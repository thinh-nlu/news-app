package com.example.news_app.data.local

import androidx.paging.PagingData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.example.news_app.domain.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM articles")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE url =:url")
    fun getArticle(url: String): Article?

}