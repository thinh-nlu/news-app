package com.example.news_app.presentation.intro

sealed class IntroEvent {
    data object SaveAppEntry: IntroEvent()
}