package com.joancolmenerodev.library_base.repository

import com.joancolmenerodev.library_base.exceptions.ServerException
import com.joancolmenerodev.library_base.exceptions.ServiceException
import com.joancolmenerodev.library_base.exceptions.SomethingWentWrongException

open class BaseRepository {

    inline fun <T> execute(block: () -> T): T =
        try {
            block()
        } catch (error: ServiceException) {
            when (error) {
                is ServerException.ServiceUnavailable -> throw SomethingWentWrongException(error)
                else -> throw error
            }
        }
}