package com.example.honkaihelper.login.data

import com.example.honkaihelper.login.data.models.LoginResponse

interface LoginRepository {
    suspend fun login(login: String, password: String): Result<LoginResponse>
}