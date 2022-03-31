package com.sachinsandbhor.articles.data.entity

data class ArticlesEntity(
    var articles: List<ArticleEntity> = emptyList(),
    var status: String? = null,
    val totalResults: Int?
)
