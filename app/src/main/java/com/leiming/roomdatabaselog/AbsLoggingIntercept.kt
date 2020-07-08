package com.leiming.roomdatabaselog

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

abstract class AbsLoggingIntercept : Interceptor {
    abstract fun process(startReq: Long, request: Request, endReq: Long, response: Response)

    abstract fun insert(request: Request)

    abstract fun printInfo(request: Request, startReq: Long, endReq: Long)

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val startReq = System.currentTimeMillis()

        request = request.newBuilder()
                .removeHeader("Accept-Encoding")
                .build();

        val response = chain.proceed(request)
        val endReq = System.currentTimeMillis()

        process(startReq, request, endReq, response)
        insert(request)
        printInfo(request, startReq, endReq)
        return response
    }
}