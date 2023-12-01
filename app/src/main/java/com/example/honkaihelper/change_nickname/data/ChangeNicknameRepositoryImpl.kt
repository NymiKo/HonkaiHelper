package com.example.honkaihelper.change_nickname.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
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