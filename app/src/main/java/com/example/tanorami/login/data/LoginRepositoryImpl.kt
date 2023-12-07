package com.example.tanorami.login.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.login.data.models.LoginRequest
import com.example.tanorami.login.data.models.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val loginService: LoginService
) : LoginRepository {
    override suspend fun login(login: String, password: String): NetworkResult<LoginResponse> {
        return withContext(ioDispatcher) {
            handleApi { loginService.login(LoginRequest(login, password)) }
        }
    }
}