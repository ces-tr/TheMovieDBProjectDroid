package com.cestr.themoviedb.retrofit.okHttpInterceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.*

class IOExceptionUrlInfoInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response

        try {
            response = chain.proceed(chain.request())
        } catch (e: IOException) {
            throw HttpIOException(chain.request(), e)
        }

        return response
    }

    private class HttpIOException internal constructor(request: Request, e: IOException) :
        IOException(String.format(Locale.US, "IOException during http request: %s", request), e)
}