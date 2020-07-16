package com.anangkur.jetpackcomposeplayground.data

import com.anangkur.jetpackcomposeplayground.data.remote.model.GetNewsResponse

interface Repository {
    suspend fun getNews(): GetNewsResponse
}