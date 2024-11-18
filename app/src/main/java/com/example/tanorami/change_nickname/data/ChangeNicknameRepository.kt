package com.example.tanorami.change_nickname.data

interface ChangeNicknameRepository {
    suspend fun changeNickname(newNickname: String): com.example.data.remote.NetworkResult<Boolean>
}