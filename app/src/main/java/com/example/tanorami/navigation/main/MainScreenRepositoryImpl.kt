package com.example.tanorami.navigation.main

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val mainScreenService: MainScreenService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : MainScreenRepository {
    override suspend fun getRemoteVersionDB(): NetworkResult<String> = withContext(ioDispatcher) {
        handleApi {
            mainScreenService.getRemoteVersionDB()
        }
    }
}