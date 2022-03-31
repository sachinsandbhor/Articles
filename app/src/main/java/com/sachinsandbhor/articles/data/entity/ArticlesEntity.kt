package com.sachinsandbhor.articles.data.entity

data class ArticlesEntity(
    val articleEntities: List<ArticleEntity>,
    val status: String,
    val totalResults: Int
)
