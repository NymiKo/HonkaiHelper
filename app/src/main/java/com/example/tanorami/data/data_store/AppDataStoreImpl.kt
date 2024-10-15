package com.example.tanorami.data.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : AppDataStore {

    private companion object {
        val TOKEN = stringPreferencesKey("token")
        val VERSION_DB = stringPreferencesKey("VERSION_DB")
    }

    override val tokenUser: Flow<String> = dataStore.data.map { preferences ->
        preferences[TOKEN] ?: ""
    }

    override val versionDB: Flow<String> = dataStore.data.map { preferences ->
        preferences[VERSION_DB] ?: ""
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
}