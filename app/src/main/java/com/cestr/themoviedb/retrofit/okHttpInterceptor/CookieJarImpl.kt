package com.cestr.themoviedb.retrofit.okHttpInterceptor

import okhttp3.Cookie
import okhttp3.HttpUrl

import okhttp3.CookieJar

class CookieJarImpl constructor() : CookieJar {

    private val cookieStore: HashMap<String, List<Cookie>>  = HashMap()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore.put(url.host(), cookies)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookies = cookieStore.get(url.host())

        if (cookies != null) {
            return cookies
        }
        else {
            val mutableList: MutableList<Cookie> = arrayListOf()
            return mutableList
        }
    }

}