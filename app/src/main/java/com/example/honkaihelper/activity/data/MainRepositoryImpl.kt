package com.example.honkaihelper.activity.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val mainService: MainService
): MainRepository {

    override suspend fun getRemoteVersionDB(): NetworkResult<String> {
        return withContext(ioDispatcher) {
            handleApi {
                mainService.getVersionDB()
            }
        }
    }
}