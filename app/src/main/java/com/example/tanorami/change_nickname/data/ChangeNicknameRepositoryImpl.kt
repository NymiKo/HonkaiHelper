package com.example.tanorami.change_nickname.data

import com.example.tanorami.core.di.IODispatcher
import com.example.tanorami.core.network.NetworkResult
import com.example.tanorami.core.network.handleApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChangeNicknameRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val changeNicknameService: ChangeNicknameService
): ChangeNicknameRepository {
    override suspend fun changeNickname(newNickname: String): NetworkResult<Boolean> = withContext(ioDispatcher) {
        return@withContext handleApi { changeNicknameService.changeNickname(newNickname) }
    }
}