package com.example.tanorami.core.data.source.local.data_store

import kotlinx.coroutines.flow.Flow

interface AppDataStore {
    val tokenUser: Flow<String>
    val versionDB: Flow<String>

    suspend fun saveToken(token: String)
    suspend fun clearToken()

    suspend fun saveVersionDB(versionDB: String)
}