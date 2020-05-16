package com.joancolmenerodev.library_base.exceptions

class SomethingWentWrongException(override val cause: Exception? = null) : Exception(cause)