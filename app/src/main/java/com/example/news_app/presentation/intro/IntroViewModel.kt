package com.example.news_app.presentation.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.domain.usecase.app_entry.AppEntryUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel
@Inject constructor(
    private val appEntryUsecase: AppEntryUsecase
): ViewModel(){

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUsecase.saveAppEntry
        }
    }

    fun onEvent(event: IntroEvent) {
        when(event) {
            is IntroEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }
}