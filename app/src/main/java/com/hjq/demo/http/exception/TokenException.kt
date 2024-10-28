package com.hjq.demo.http.exception

import com.hjq.http.exception.HttpException

class TokenException : HttpException {
    constructor(message: String?) : super(message)

    constructor(message: String?, cause: Throwable?) : super(message, cause)
}