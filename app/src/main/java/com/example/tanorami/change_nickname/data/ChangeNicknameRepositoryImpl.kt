package com.example.tanorami.change_nickname.data

import com.example.data.remote.util.NetworkResult
import com.example.data.remote.util.handleApi
import com.example.domain.di.DispatcherIo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChangeNicknameRepositoryImpl @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val changeNicknameService: ChangeNicknameService,
): ChangeNicknameRepository {
    override suspend fun changeNickname(newNickname: String): NetworkResult<Boolean> =
        withContext(ioDispatcher) {
            return@withContext handleApi {
                changeNicknameService.changeNickname(
                    newNickname
                )
            }
    }
}