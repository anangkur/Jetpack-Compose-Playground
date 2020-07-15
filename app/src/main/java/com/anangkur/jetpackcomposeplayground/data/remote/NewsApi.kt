package com.anangkur.jetpackcomposeplayground.data.remote

import com.anangkur.jetpackcomposeplayground.data.remote.model.GetNewsResponse
import com.google.gson.Gson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlinesNews(
        @Query("apiKey") apiKey: String? = API_KEY,
        @Query("country") country: String? = COUNTRY_ID,
        @Query("category") category: String? = CATEGORY_GENERAL
    ): GetNewsResponse

    companion object Factory{

        const val API_KEY = "261d82dd7e26494e841fb1039a4fdaf7"
        const val COUNTRY_ID = "id"
        const val CATEGORY_GENERAL = "general"

        val getApiService: NewsApi by lazy {

            val mClient =
                OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor { chain ->
                        val request =
                            chain.request()
                                .newBuilder()
                                .build()
                        chain.proceed(request)
                    }
                    .build()

            val mRetrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .build()

            mRetrofit.create(NewsApi::class.java)
        }
    }
}