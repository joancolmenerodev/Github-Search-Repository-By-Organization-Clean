package com.joancolmenerodev.networking.di

import com.joancolmenerodev.networking.BuildConfig
import com.joancolmenerodev.networking.retrofit.ErrorInterceptor
import com.joancolmenerodev.networking.retrofit.RetrofitServiceModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [RetrofitServiceModule::class])
object TestNetworkingModule {

    @Provides
    @Singleton
    fun provideTestRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideTestOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        errorInterceptor: ErrorInterceptor
    ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(errorInterceptor)
        .cache(null)
        .build()

    @Provides
    @Singleton
    fun provideTestHttpLogginInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

}

private const val BASE_URL = "http://localhost:8681/"