package com.example.data.db.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.domain.data_store.AppDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : AppDataStore {

    private companion object {
        val TOKEN = stringPreferencesKey("token")
        val VERSION_DB = stringPreferencesKey("VERSION_DB")
        val VERSION_IMPORTANT_MESSAGE = intPreferencesKey("VERSION_IMPORTANT_MESSAGE")
        val SNOWFALL_ANIMATION = booleanPreferencesKey("SNOWFALL_ANIMATION")
        val COUNT_SNOWFALL = floatPreferencesKey("COUNT_SNOWFALL")
    }

    override val tokenUser: Flow<String> = dataStore.data.map { preferences ->
        preferences[TOKEN] ?: ""
    }

    override val versionDB: Flow<String> =
        dataStore.data.map { preferences ->
            preferences[VERSION_DB] ?: ""
        }

    override val versionImportantMessage: Flow<Int> = dataStore.data.map { preference ->
        preference[VERSION_IMPORTANT_MESSAGE] ?: 0
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences[TOKEN] = ""
        }
    }

    override suspend fun saveVersionDB(versionDB: String) {
        dataStore.edit { preferences ->
            preferences[VERSION_DB] = versionDB
        }
    }

    override suspend fun saveVersionImportantMessage(version: Int) {
        dataStore.edit { preference ->
            preference[VERSION_IMPORTANT_MESSAGE] = version
        }
    }

    override val showSnowfallAnimation: Flow<Boolean> = dataStore.data.map { preference ->
        preference[SNOWFALL_ANIMATION] != false
    }
    override val countSnowflakes: Flow<Float> = dataStore.data.map { preference ->
        preference[COUNT_SNOWFALL] ?: 80F
    }

    override suspend fun saveSettingsSnowfallAnimation(show: Boolean) {
        dataStore.edit { preference ->
            preference[SNOWFALL_ANIMATION] = show
        }
    }

    override suspend fun saveCountSnowflakesAnimation(countSnowfall: Float) {
        dataStore.edit { preference ->
            preference[COUNT_SNOWFALL] = countSnowfall
        }
    }
}