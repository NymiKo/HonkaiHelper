package com.example.tanorami.activity.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.heroes.data.HeroesListService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val mainService: MainService,
    private val heroesListService: HeroesListService,
): MainRepository {

    override suspend fun getRemoteVersionDB(): NetworkResult<String> {
        return withContext(ioDispatcher) {
            handleApi {
                mainService.getVersionDB()
            }
        }
    }

    override suspend fun getAvatar(): NetworkResult<String> {
        return withContext(ioDispatcher) {
            handleApi {
                heroesListService.getAvatar()
            }
        }
    }
}