package com.sachinsandbhor.articles.data.entity

data class ArticleEntity(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceEntity: SourceEntity,
    val title: String,
    val url: String,
    val urlToImage: String
)