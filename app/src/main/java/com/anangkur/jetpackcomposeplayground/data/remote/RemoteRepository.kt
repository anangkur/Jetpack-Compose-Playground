package com.anangkur.jetpackcomposeplayground.data.remote

import com.anangkur.jetpackcomposeplayground.data.Repository
import com.anangkur.jetpackcomposeplayground.data.remote.model.GetNewsResponse

class RemoteRepository(
    private val newsApi: NewsApi
) : Repository {

    override suspend fun getNews(): GetNewsResponse {
        return newsApi.getTopHeadlinesNews()
    }
}