package com.anangkur.jetpackcomposeplayground.data.remote

import com.anangkur.jetpackcomposeplayground.data.Repository
import com.anangkur.jetpackcomposeplayground.data.remote.model.ArticleModel
import com.anangkur.jetpackcomposeplayground.data.remote.model.GetNewsResponse
import com.anangkur.jetpackcomposeplayground.model.Result

class RemoteRepository(
    private val newsApi: NewsApi
) : Repository {

    override suspend fun getNews(): GetNewsResponse {
        return newsApi.getTopHeadlinesNews()
    }
}