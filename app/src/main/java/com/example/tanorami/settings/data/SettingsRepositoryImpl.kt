package com.example.tanorami.settings.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.main.data.MainScreenService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val mainScreenService: MainScreenService
): SettingsRepository {
    override suspend fun checkUpdate(): NetworkResult<String> = withContext(ioDispatcher) {
        when (val result = handleApi { mainScreenService.getRemoteVersionDB() }) {
            is NetworkResult.Error -> {
                return@withContext NetworkResult.Error(result.code)
            }

            is NetworkResult.Success -> {
                return@withContext NetworkResult.Success(result.data.remoteVersionDB)
            }
        }
    }
}