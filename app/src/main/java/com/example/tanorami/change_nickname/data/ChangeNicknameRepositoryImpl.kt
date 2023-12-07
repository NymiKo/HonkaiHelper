package com.example.tanorami.change_nickname.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChangeNicknameRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val changeNicknameService: ChangeNicknameService
): ChangeNicknameRepository {
    override suspend fun changeNickname(newNickname: String): NetworkResult<Boolean> = withContext(ioDispatcher) {
        return@withContext handleApi { changeNicknameService.changeNickname(newNickname) }
    }
}