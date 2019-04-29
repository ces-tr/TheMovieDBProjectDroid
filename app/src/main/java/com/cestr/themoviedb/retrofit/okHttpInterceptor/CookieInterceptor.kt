package com.cestr.themoviedb.retrofit.okHttpInterceptor

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Request.Builder
import okhttp3.Response

class CookieInterceptor() : Interceptor
{
    val TAG : String = "AddCookieInterceptor";

    override fun intercept(paramChain: Interceptor.Chain ): Response {

                val localBuilder:Builder = paramChain.request().newBuilder()
                localBuilder.addHeader("x-privalia-app", "app")
                localBuilder.addHeader("x-privalia-device", "smartphone")//PrivaliaApplication.getDeviceType());
                localBuilder.addHeader("x-privalia-platform-system", "Android");
                localBuilder.addHeader("x-privalia-platform-system-version", Build.VERSION.RELEASE);
                localBuilder.addHeader("x-privalia-app-version", "4.7.8")//PrivaliaApplication.getApplicationVersion());
                return paramChain.proceed(localBuilder.build());
            }
}