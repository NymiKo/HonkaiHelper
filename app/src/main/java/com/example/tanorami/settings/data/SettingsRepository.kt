package com.example.tanorami.settings.data

import com.example.data.remote.util.NetworkResult

interface SettingsRepository {
    suspend fun checkUpdate(): NetworkResult<String>
}