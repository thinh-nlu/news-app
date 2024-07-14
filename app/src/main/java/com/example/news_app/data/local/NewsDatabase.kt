package com.example.news_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news_app.domain.model.Article

@Database(entities = [Article::class], version = 1)
abstract class NewsDatabase : RoomDatabase(){

    abstract val newsDAO: NewsDAO
}