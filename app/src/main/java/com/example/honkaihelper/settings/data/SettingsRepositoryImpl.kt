package com.example.honkaihelper.settings.data

import com.example.honkaihelper.activity.data.MainService
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val mainService: MainService
): SettingsRepository {
    override suspend fun checkUpdate(): NetworkResult<String> = withContext(ioDispatcher) {
        return@withContext handleApi { mainService.getVersionDB() }
    }
}