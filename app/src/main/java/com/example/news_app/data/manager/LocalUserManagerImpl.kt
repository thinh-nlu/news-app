package com.example.news_app.data.manager

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.news_app.domain.manager.LocalUserManager
import com.example.news_app.presentation.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val application: Application
): LocalUserManager {
    override suspend fun saveEntryApp() {
        application.dataStore.edit {
            settings ->
            settings[PreferrenceKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return application.dataStore.data.map {
            preferrences ->
            preferrences[PreferrenceKeys.APP_ENTRY] ?: false
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constant.USER_SETTING)

private object PreferrenceKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constant.APP_ENTRY)
}