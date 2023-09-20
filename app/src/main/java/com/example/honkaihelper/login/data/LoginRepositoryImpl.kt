package com.example.honkaihelper.login.data

import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.login.data.models.LoginRequest
import com.example.honkaihelper.login.data.models.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val loginService: LoginService
): LoginRepository {
    override suspend fun login(login: String, password: String): Result<LoginResponse> {
        return withContext(ioDispatcher) {
            handleApi { loginService.login(LoginRequest(login, password)) }
        }
    }
}