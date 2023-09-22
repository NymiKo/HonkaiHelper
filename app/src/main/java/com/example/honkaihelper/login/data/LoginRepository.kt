package com.example.honkaihelper.login.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.login.data.models.LoginResponse

interface LoginRepository {
    suspend fun login(login: String, password: String): NetworkResult<LoginResponse>
}