package com.example.tanorami.change_nickname.data

import com.example.data.remote.util.NetworkResult

interface ChangeNicknameRepository {
    suspend fun changeNickname(newNickname: String): NetworkResult<Boolean>
}