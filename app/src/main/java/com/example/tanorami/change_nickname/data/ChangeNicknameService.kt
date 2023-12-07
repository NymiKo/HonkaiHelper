package com.example.tanorami.change_nickname.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChangeNicknameService {

    @GET("/changeNickname.php")
    suspend fun changeNickname(@Query("newNickname") newNickname: String): Response<Boolean>
}