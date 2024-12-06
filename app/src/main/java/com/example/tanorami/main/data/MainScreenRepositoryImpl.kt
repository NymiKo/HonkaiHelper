package com.example.tanorami.main.data

import com.example.data.remote.util.handleApi
import com.example.domain.di.DispatcherIo
import com.example.domain.util.NetworkResult
import com.example.tanorami.main.presentation.models.NewDataModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val mainScreenService: MainScreenService,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
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
                mainScreenService.getAvatar()
            }
        }
    }
}