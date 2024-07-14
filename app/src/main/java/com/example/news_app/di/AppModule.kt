package com.example.news_app.di

import android.app.Application
import androidx.room.Room
import com.example.news_app.data.local.NewsDAO
import com.example.news_app.data.local.NewsDatabase
import com.example.news_app.data.local.NewsTypeConverter
import com.example.news_app.data.manager.LocalUserManagerImpl
import com.example.news_app.data.remote.NewAPI
import com.example.news_app.data.repository.NewsRepositoryImpl
import com.example.news_app.domain.manager.LocalUserManager
import com.example.news_app.domain.repository.NewsRepository
import com.example.news_app.domain.usecase.app_entry.AppEntryUsecase
import com.example.news_app.domain.usecase.app_entry.ReadAppEntry
import com.example.news_app.domain.usecase.app_entry.SaveAppEntry
import com.example.news_app.domain.usecase.news.GetNews
import com.example.news_app.domain.usecase.news.NewsUsecase
import com.example.news_app.domain.usecase.news.SearchNews
import com.example.news_app.presentation.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppUsecase(
        localUserManager: LocalUserManager
    ) = AppEntryUsecase(
        readAppEntry = ReadAppEntry(localUserManager = localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager = localUserManager)
    )

    //chay singleton get api truoc
    @Provides
    @Singleton
    fun provideApiInstance(): NewAPI {
        return Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewAPI::class.java)
    }

    //sau khi co api thi load data vao repo
    @Provides
    @Singleton
    fun provideNewsRepository(
        newsAPI: NewAPI
    ): NewsRepository {
        return NewsRepositoryImpl(newsAPI)
    }

    @Provides
    @Singleton
    fun provideNewsUsecase(
        newsRepository: NewsRepository
    ): NewsUsecase {
        return NewsUsecase(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = Constant.DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDAO(
        newsDatabase: NewsDatabase
    ): NewsDAO = newsDatabase.newsDAO
}