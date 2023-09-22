package com.example.honkaihelper.login.data

import com.example.honkaihelper.login.data.models.LoginRequest
import com.example.honkaihelper.login.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login.php")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}