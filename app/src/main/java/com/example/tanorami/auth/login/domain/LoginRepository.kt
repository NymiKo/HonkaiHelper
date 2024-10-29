package com.example.tanorami.auth.login.domain

import com.example.tanorami.auth.login.data.models.LoginResponse
import com.example.tanorami.core.data.NetworkResult

interface LoginRepository {
    suspend fun login(login: String, password: String): NetworkResult<LoginResponse>
}