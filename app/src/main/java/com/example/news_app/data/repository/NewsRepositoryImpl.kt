package com.example.news_app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news_app.data.remote.NewAPI
import com.example.news_app.data.remote.NewsPagingSource
import com.example.news_app.domain.model.Article
import com.example.news_app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newAPI: NewAPI
): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newAPI,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}