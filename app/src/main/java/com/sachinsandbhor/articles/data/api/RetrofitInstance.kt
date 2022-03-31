package com.sachinsandbhor.articles.data.api

import com.sachinsandbhor.articles.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

private const val BASE_URL="https://newsapi.org/v2/"
object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val articlesApi: ArticlesApi by lazy {
        retrofit.create(ArticlesApi::class.java)
    }

    private fun httpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(BasicAuthInterceptor())
        clientBuilder.readTimeout(120, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(120, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    class BasicAuthInterceptor: Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val newsUrl = request.url().newBuilder().addQueryParameter("apiKey", BuildConfig.API_KEY).build()
            val newRequest = request.newBuilder().url(newsUrl).build()
            return chain.proceed(newRequest)
        }

    }
}