package com.example.news_app.presentation.intro

import androidx.annotation.DrawableRes
import com.example.news_app.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf<Page>(
    Page(
        "Lorem ipsum is simply dummy",
        "Lorem ipsum is simply dummy text of the printing and typesetting inddustry",
        R.drawable.onboarding1
    ),
    Page(
        "Lorem ipsum is simply dummy",
        "Lorem ipsum is simply dummy text of the printing and typesetting inddustry",
        R.drawable.onboarding2
    ),
    Page(
        "Lorem ipsum is simply dummy",
        "Lorem ipsum is simply dummy text of the printing and typesetting inddustry",
        R.drawable.onboarding3
    ),
)
