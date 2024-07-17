package com.example.news_app.domain.usecase.news

import androidx.paging.PagingData
import com.example.news_app.data.local.NewsDAO
import com.example.news_app.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetAllArticle(
    private val newsDAO: NewsDAO
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDAO.getArticles()
    }
}