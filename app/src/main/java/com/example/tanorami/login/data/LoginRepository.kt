package com.example.tanorami.login.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.login.data.models.LoginResponse

interface LoginRepository {
    suspend fun login(login: String, password: String): NetworkResult<LoginResponse>
}