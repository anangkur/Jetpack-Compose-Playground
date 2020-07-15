package com.anangkur.jetpackcomposeplayground.data

import com.anangkur.jetpackcomposeplayground.data.remote.model.ArticleModel
import com.anangkur.jetpackcomposeplayground.model.Result

interface Repository {
    suspend fun getNews(callback: (Result<List<ArticleModel>>) -> Unit)
}