package com.example.news_app.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.news_app.domain.usecase.news.NewsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUsecase: NewsUsecase
): ViewModel(){

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private fun searchNew() {
        val articles = newsUsecase.searchNews(
            searchQuery = state.value.searchQuery,
            sources = listOf("bbc-news","abc-news","al-jazeera-english")
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)
    }


    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(
                    searchQuery = event.searchQuery
                )
            }
            is SearchEvent.SearchNews -> {
                searchNew()
            }
        }
    }
}