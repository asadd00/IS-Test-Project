package com.myubuntu.istestproject.repository

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {
    private var retrofit: Retrofit? = null

    @JvmStatic
    public fun getAPIService(): Api {
        return getClient()!!.create(Api::class.java)
    }

    private fun getClient(): Retrofit? {
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.addInterceptor((Interceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .build()
                )
            }))
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL+""+ API_V1)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit
    }

    private const val API_V1 = "app/"
    private const val DEVELOPMENT_SERVER = "http://94.206.102.22/"

    private const val BASE_URL = DEVELOPMENT_SERVER

    const val LOGIN = "authenticate"
    const val GET_PRODUCTS = "item-list"
}