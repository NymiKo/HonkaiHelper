package com.example.tanorami.settings.data

import com.example.tanorami.activity.data.MainService
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
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