package com.example.tanorami.change_nickname.data

import com.example.core.network.NetworkResult

interface ChangeNicknameRepository {
    suspend fun changeNickname(newNickname: String): NetworkResult<Boolean>
}