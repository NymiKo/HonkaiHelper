package com.example.tanorami.auth.login.domain

import com.example.data.remote.util.NetworkResult
import com.example.tanorami.auth.login.data.models.LoginResponse

interface LoginRepository {
    suspend fun login(
        login: String,
        password: String,
    ): NetworkResult<LoginResponse>
}