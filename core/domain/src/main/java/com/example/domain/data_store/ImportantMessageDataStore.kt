package com.example.domain.data_store

import kotlinx.coroutines.flow.Flow

interface ImportantMessageDataStore {
    val versionImportantMessage: Flow<Int>

    suspend fun saveVersionImportantMessage(version: Int)
}