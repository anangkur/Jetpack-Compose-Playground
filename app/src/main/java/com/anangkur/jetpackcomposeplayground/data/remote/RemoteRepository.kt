package com.anangkur.jetpackcomposeplayground.data.remote

import com.anangkur.jetpackcomposeplayground.data.Repository
import com.anangkur.jetpackcomposeplayground.data.remote.model.ArticleModel
import com.anangkur.jetpackcomposeplayground.model.Result

class RemoteRepository(
    private val newsApi: NewsApi
) : Repository {

    override suspend fun getNews(callback: (Result<List<ArticleModel>>) -> Unit) {
        try {
            val response = newsApi.getTopHeadlinesNews()
            if (response.status == "ok") {
                callback(Result.Success(response.articles))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            callback(Result.Error(e))
        }
    }
}