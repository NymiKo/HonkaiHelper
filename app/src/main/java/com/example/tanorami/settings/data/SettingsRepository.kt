package com.example.tanorami.settings.data

import com.example.tanorami.core.data.NetworkResult


interface SettingsRepository {
    suspend fun checkUpdate(): NetworkResult<String>
}