package com.cestr.testkotlin.Utils

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class UserAgentInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(paramChain: Interceptor.Chain): Response {

        var localObject1: Any = StringBuilder()
        (localObject1 as StringBuilder).append(Build.MODEL)
        localObject1.append(" Build/")
        localObject1.append(Build.BOARD)
        localObject1 = localObject1.toString()
        var localObject2: Any = Build.VERSION.RELEASE
        val localStringBuilder = StringBuilder()
        localStringBuilder.append("Android/build:com.privalia.privalia/4.7.8 (")
        localStringBuilder.append(localObject1)
        localStringBuilder.append("; Android ")
        localStringBuilder.append(localObject2 as String)
        localStringBuilder.append(") AndroidDevKit")
        localObject1 = localStringBuilder.toString()
        localObject2 = paramChain.request()
        return paramChain.proceed(
            (localObject2 as Request).newBuilder().header(
                "User-Agent",
                localObject1
            ).method((localObject2 as Request).method(), (localObject2 as Request).body()).build()
        )
    }
}