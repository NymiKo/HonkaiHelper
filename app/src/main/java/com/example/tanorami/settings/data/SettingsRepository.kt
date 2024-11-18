package com.example.tanorami.settings.data

interface SettingsRepository {
    suspend fun checkUpdate(): com.example.data.remote.NetworkResult<String>
}