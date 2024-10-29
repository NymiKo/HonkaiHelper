package com.example.tanorami.change_nickname.data

import com.example.tanorami.core.data.NetworkResult


interface ChangeNicknameRepository {
    suspend fun changeNickname(newNickname: String): NetworkResult<Boolean>
}