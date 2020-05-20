package com.joancolmenerodev.organization_searcher

import com.joancolmenerodev.library_base.exceptions.ClientException
import com.joancolmenerodev.library_base.exceptions.NoInternetConnection
import com.joancolmenerodev.library_base.exceptions.ServerException
import com.joancolmenerodev.library_base.exceptions.ServiceException
import com.joancolmenerodev.organization_searcher.feature.organization_list.data.repository.GithubService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection

abstract class IntegrationTest {

    lateinit var mockServer: MockWebServer

    lateinit var apiService: GithubService

    @Before
    open fun setUp() {
        this.configureMockServer()
        generateFakeApiService()
    }

    @After
    open fun tearDown() {
        this.stopMockServer()
    }

    open fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    open fun stopMockServer() {
        mockServer.shutdown()
    }

    open fun mockHttpResponse(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName))
    )

    private fun getJson(filename: String): String {
        this.javaClass.classLoader?.let {
            val uri = it.getResource("json/${filename}")
            val file = File(uri.path)
            return String(file.readBytes())
        }
        throw NullPointerException("ClassLoader is null")
    }

    private fun generateFakeApiService() {
        apiService = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .client(generateOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }

    private fun generateOkHttpClient() = OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(ErrorInterceptor())
        .build()
}

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response =
            try {
                chain.proceed(chain.request())
            } catch (error: IOException) {
                throw NoInternetConnection(error.cause)
            }
        if (!response.isSuccessful) {
            when (response.code) {
                HttpURLConnection.HTTP_UNAVAILABLE -> throw ServerException.ServiceUnavailable
                HttpURLConnection.HTTP_NOT_FOUND -> throw ClientException.NotFound
                HttpURLConnection.HTTP_CLIENT_TIMEOUT -> throw ClientException.RequestTimeout
                else -> throw ServiceException(
                    IllegalStateException("The status code ${response.code} was received but not handled!")
                )
            }
        }
        return response
    }
}