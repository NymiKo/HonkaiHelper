package com.example.tanorami.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tanorami.di.DBSharedPrefMigrate
import com.example.tanorami.di.UserSharedPrefMigrate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataStoreImpl @Inject constructor(
    @UserSharedPrefMigrate private val userDataStore: DataStore<Preferences>,
    @DBSharedPrefMigrate private val dbDataStore: DataStore<Preferences>,
) : AppDataStore {

    private companion object {
        val TOKEN = stringPreferencesKey("token")
        val VERSION_DB = stringPreferencesKey("VERSION_DB")
    }

    override val tokenUser: Flow<String> = userDataStore.data.map { preferences ->
        preferences[TOKEN] ?: ""
    }

    override val versionDB: Flow<String> = dbDataStore.data.map { db ->
        db[VERSION_DB] ?: ""
    }

    override suspend fun saveToken(token: String) {
        userDataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    override suspend fun clearToken() {
        userDataStore.edit { preferences ->
            preferences[TOKEN] = ""
        }
    }

    override suspend fun saveVersionDB(versionDB: String) {
        dbDataStore.edit { db ->
            db[VERSION_DB] = versionDB
        }
    }
}