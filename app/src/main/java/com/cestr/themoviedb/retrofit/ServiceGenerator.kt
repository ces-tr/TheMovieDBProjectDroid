package com.cestr.themoviedb.retrofit

import com.cestr.themoviedb.BuildConfig
import com.cestr.themoviedb.retrofit.okHttpInterceptor.IOExceptionUrlInfoInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.Cache
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File


class RetrofitServiceFactory {

    companion object {

        private const val BASE_API_URL = "http://api.themoviedb.org/3/"
        const val API_KEY_PARAMETER = "?api_key=" + BuildConfig.TMDB_API_KEY

        private var retrofit: Retrofit? = null
        //private val gson = GsonBuilder().create()
        private val moshijson = MoshiConverterFactory.create()


        private var _cache: Cache? = null
        private val cache: Cache?
            get() {
                if (_cache == null) {
                   try {
                        val cacheFile = File("responses")
                        _cache = Cache(cacheFile, 10 * 1024 * 1024)
                   } catch (e: Exception) {
                       e.printStackTrace()
                   }
                }
                return _cache
            }


        private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val ioExceptionUrlInfoInterceptor=  IOExceptionUrlInfoInterceptor()


        private val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
                                                                .cache(cache)
                                                                .addInterceptor(httpLoggingInterceptor)
                                                                .addInterceptor(ioExceptionUrlInfoInterceptor)

        private val okHttpClient = okHttpClientBuilder.build()

        fun <T> createService(serviceClass: Class<T>): T {

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(moshijson)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return retrofit!!.create(serviceClass)
        }

    }


}