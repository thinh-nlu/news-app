package com.example.news_app.domain.usecase.app_entry

import com.example.news_app.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveEntryApp()
    }
}