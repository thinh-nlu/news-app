package com.example.news_app.data.remote

import com.example.news_app.data.remote.dto.NewsResponse
import com.example.news_app.presentation.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface NewAPI {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("source") sources: String,
        @Query("apiKey") apiKey: String = Constant.API_KEY
    ): NewsResponse
}