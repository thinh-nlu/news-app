package com.example.news_app.domain.usecase.news

import androidx.paging.PagingData
import com.example.news_app.domain.model.Article
import com.example.news_app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}