package com.sachinsandbhor.articles.data.api

import com.sachinsandbhor.articles.data.entity.ArticlesEntity
import retrofit2.http.GET

interface ArticlesApi {

    @GET("top-headlines?country=in")
    suspend fun getTopArticles(): ArticlesEntity
}