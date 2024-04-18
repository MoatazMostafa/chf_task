package com.example.chftask.common.network

import com.example.chftask.common.network.deserializer.AnnotationExclusionStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class ServerRequest(url: String) {

    private val gson: Gson = GsonBuilder()
        .addDeserializationExclusionStrategy(AnnotationExclusionStrategy)
        .create()

    protected val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(url)
        .client(provideOkHttpClient())
        .build()

    private fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

//    private class AuthorizationInterceptor(val accessToken:String) : Interceptor {
//        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//            val request = chain.request().newBuilder()
//                .addHeader(
//                    "Authorization",
//                    "Bearer $accessToken"
//                )
//                .addHeader("accept", "application/json")
//                .build()
//
//            return chain.proceed(request)
//        }
//    }
}
