package com.example.tanorami.main.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.heroes.data.HeroesListService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val mainScreenService: MainScreenService,
    private val heroesListService: HeroesListService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : MainScreenRepository {
    override suspend fun getRemoteVersionDB(): NetworkResult<String> = withContext(ioDispatcher) {
        handleApi {
            mainScreenService.getRemoteVersionDB()
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