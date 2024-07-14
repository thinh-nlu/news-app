package com.example.news_app.domain.manager

import kotlinx.coroutines.flow.Flow


interface LocalUserManager {
    suspend fun saveEntryApp()
    fun readAppEntry(): Flow<Boolean>
}