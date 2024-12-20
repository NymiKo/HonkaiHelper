package com.example.tanorami.settings.data

import com.example.domain.util.NetworkResult

interface SettingsRepository {
    suspend fun checkUpdate(): NetworkResult<String>
}