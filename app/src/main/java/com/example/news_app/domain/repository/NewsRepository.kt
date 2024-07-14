package com.example.news_app.domain.repository

import com.example.news_app.domain.model.Article
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}