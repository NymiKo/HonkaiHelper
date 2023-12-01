package com.example.honkaihelper.change_nickname.data

import com.example.honkaihelper.data.NetworkResult

interface ChangeNicknameRepository {
    suspend fun changeNickname(newNickname: String): NetworkResult<Boolean>
}