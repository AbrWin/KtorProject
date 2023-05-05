package com.abrsoftware.util

import io.ktor.http.*

sealed class Response<T>(
    val code: HttpStatusCode = HttpStatusCode.OK,
    val data: T
){
    class Succes<T>(data: T): Response<T>(data = data)
    class Error<T>(code: HttpStatusCode, data: T):  Response<T>(code, data)
}