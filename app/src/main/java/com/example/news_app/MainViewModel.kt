package com.example.news_app

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.domain.usecase.app_entry.AppEntryUsecase
import com.example.news_app.presentation.navgraph.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val appEntryUsecase: AppEntryUsecase
): ViewModel() {
    private val _splashCondition = mutableStateOf(false)
    val splashCondition: State<Boolean> = _splashCondition

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUsecase.readAppEntry().onEach {
            shouldStartFromHome ->
            if (shouldStartFromHome) {
                startDestination = Route.NewsNavigation.route
            } else {
                startDestination = Route.AppStartNavigation.route
            }
            delay(300)
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}