package com.example.news_app.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.domain.model.Article
import com.example.news_app.domain.usecase.news.GetArticle
import com.example.news_app.domain.usecase.news.NewsUsecase
import com.example.news_app.presentation.utils.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUsecase: NewsUsecase
): ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    private suspend fun insertArticle(article: Article) {
        newsUsecase.insertArticle(article)
        sideEffect = UIComponent.Toast("Article inserted")
    }

    private suspend fun deleteArticle(article: Article) {
        newsUsecase.deleteArticle(article)
        sideEffect = UIComponent.Toast("Article deleted")
    }

    fun onEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.InsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUsecase.getArticle(url = event.article.url)
                    if (article == null) {
                        insertArticle(event.article)
                    } else deleteArticle(event.article)
                }
            }
            is DetailEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }
}