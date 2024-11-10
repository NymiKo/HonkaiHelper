package com.example.tanorami.settings.data

import com.example.tanorami.core.network.NetworkResult


interface SettingsRepository {
    suspend fun checkUpdate(): NetworkResult<String>
}