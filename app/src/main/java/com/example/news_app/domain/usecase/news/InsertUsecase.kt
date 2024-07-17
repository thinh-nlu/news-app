package com.example.news_app.domain.usecase.news

import com.example.news_app.data.local.NewsDAO
import com.example.news_app.domain.model.Article

class InsertUsecase(
    private val newsDAO: NewsDAO
) {
    suspend operator fun invoke(article: Article) {
        newsDAO.insert(article)
    }
}