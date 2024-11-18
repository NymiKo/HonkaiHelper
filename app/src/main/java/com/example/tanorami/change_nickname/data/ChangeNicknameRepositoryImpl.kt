package com.example.tanorami.change_nickname.data

import com.example.core.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChangeNicknameRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val changeNicknameService: ChangeNicknameService
): ChangeNicknameRepository {
    override suspend fun changeNickname(newNickname: String): com.example.data.remote.NetworkResult<Boolean> =
        withContext(ioDispatcher) {
            return@withContext com.example.data.remote.handleApi {
                changeNicknameService.changeNickname(
                    newNickname
                )
            }
    }
}