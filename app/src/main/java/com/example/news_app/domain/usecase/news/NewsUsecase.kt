package com.example.news_app.domain.usecase.news

data class NewsUsecase(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val insertArticle: InsertUsecase,
    val deleteArticle: DeleteUsecase,
    val getAllArticle: GetAllArticle,
    val getArticle: GetArticle
)