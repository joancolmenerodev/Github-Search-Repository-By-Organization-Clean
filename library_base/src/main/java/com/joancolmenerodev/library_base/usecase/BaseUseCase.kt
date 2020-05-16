package com.joancolmenerodev.library_base.usecase

import com.joancolmenerodev.library_base.Either

abstract class BaseUseCase {

    inline fun <reified L : Exception, R> toEither(block: () -> R): Either<L, R> {
        return try {
            Either.Right(block())
        } catch (e: Exception) {
            when (e) {
                is L -> Either.Left(e)
                else -> throw e
            }
        }
    }
}