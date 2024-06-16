package com.example.tanorami.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserDataStore {

    private companion object {
        val TOKEN_USER = stringPreferencesKey("TOKEN_USER")
    }

    override val tokenUser: Flow<String> = dataStore.data.map { preferences ->
        preferences[TOKEN_USER] ?: ""
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_USER] = token
        }
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences[TOKEN_USER] = ""
        }
    }
}