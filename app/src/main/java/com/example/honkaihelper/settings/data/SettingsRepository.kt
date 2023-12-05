package com.example.honkaihelper.settings.data

import com.example.honkaihelper.data.NetworkResult

interface SettingsRepository {
    suspend fun checkUpdate(): NetworkResult<String>
}