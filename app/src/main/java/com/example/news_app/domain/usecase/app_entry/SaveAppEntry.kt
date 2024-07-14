package com.example.news_app.domain.usecase.app_entry

import com.example.news_app.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveEntryApp()
    }
}