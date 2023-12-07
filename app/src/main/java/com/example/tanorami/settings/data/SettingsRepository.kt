package com.example.tanorami.settings.data

import com.example.tanorami.data.NetworkResult

interface SettingsRepository {
    suspend fun checkUpdate(): NetworkResult<String>
}