package com.example.domain.data_store

import kotlinx.coroutines.flow.Flow

interface SnowfallAnimationDataStore {
    val showSnowfallAnimation: Flow<Boolean>
    val countSnowflakes: Flow<Float>

    suspend fun saveSettingsSnowfallAnimation(show: Boolean)
    suspend fun saveCountSnowflakesAnimation(countSnowflakes: Float)
}