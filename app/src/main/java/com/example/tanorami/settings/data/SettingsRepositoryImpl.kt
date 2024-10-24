package com.example.tanorami.settings.data

import com.example.tanorami.activity.data.MainService
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val splashService: MainService
): SettingsRepository {
    override suspend fun checkUpdate(): NetworkResult<String> = withContext(ioDispatcher) {
        return@withContext handleApi { splashService.getVersionDB() }
    }
}