package com.example.tanorami.main.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.heroes.data.HeroesListService
import com.example.tanorami.main.presentation.models.NewDataModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val mainScreenService: MainScreenService,
    private val heroesListService: HeroesListService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : MainScreenRepository {
    override suspend fun getRemoteVersionDB(): NetworkResult<NewDataModel> =
        withContext(ioDispatcher) {
            when (val result = handleApi { mainScreenService.getRemoteVersionDB() }) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    return@withContext NetworkResult.Success(
                        NewDataModel(
                            remoteVersionDB = result.data.remoteVersionDB,
                            message = result.data.message
                        )
                    )
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