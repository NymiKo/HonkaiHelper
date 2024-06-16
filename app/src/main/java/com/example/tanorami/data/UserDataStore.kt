package com.example.tanorami.data

import kotlinx.coroutines.flow.Flow

interface UserDataStore {
    val tokenUser: Flow<String>

    suspend fun saveToken(token: String)
    suspend fun clearToken()
}