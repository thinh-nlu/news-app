package com.example.news_app.domain.usecase.news

import com.example.news_app.data.local.NewsDAO
import com.example.news_app.domain.model.Article

class GetArticle(
    private val newsDAO: NewsDAO
) {
    suspend operator fun invoke(url: String): Article? {
        return newsDAO.getArticle(url)
    }
}