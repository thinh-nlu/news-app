package com.example.news_app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news_app.data.local.NewsDAO
import com.example.news_app.data.remote.NewAPI
import com.example.news_app.data.remote.NewsPagingSource
import com.example.news_app.data.remote.SearchPagingSource
import com.example.news_app.domain.model.Article
import com.example.news_app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newAPI: NewAPI,
    private val newsDAO: NewsDAO
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

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
                config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            SearchPagingSource(
                api = newAPI,
                searchQuery = searchQuery,
                sources = sources.joinToString(separator = ",")
            )
        }
        ).flow
    }

    override suspend fun insertArticle(article: Article) {
        newsDAO.insert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDAO.delete(article)
    }

    override fun getAllArticle(): Flow<List<Article>> {
        return newsDAO.getArticles()
    }

    override suspend fun getArticle(url: String): Article? {
        return newsDAO.getArticle(url = url)
    }
}