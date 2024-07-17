package com.example.news_app.presentation.detail

import com.example.news_app.domain.model.Article

sealed class DetailEvent {
    data class InsertDeleteArticle(val article: Article): DetailEvent()
    data object RemoveSideEffect: DetailEvent()
}