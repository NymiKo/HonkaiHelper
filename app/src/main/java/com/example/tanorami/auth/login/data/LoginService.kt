package com.example.tanorami.auth.login.data

import com.example.tanorami.auth.login.data.models.LoginRequest
import com.example.tanorami.auth.login.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login.php")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}