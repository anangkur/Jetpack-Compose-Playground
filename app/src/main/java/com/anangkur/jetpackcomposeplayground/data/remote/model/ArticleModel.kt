package com.anangkur.jetpackcomposeplayground.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticleModel(
    val id: Int = 0,
    val author: String? = "",
    val title: String? = "",
    val description: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
    val publishedAt: String? = "",
    val content: String? = "",
    val category: String? = ""
)