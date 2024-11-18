package com.example.tanorami.auth.login.domain

import com.example.core.di.IODispatcher
import com.example.tanorami.auth.login.data.LoginService
import com.example.tanorami.auth.login.data.models.LoginRequest
import com.example.tanorami.auth.login.data.models.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val loginService: LoginService
) : LoginRepository {
    override suspend fun login(
        login: String,
        password: String
    ): com.example.data.remote.NetworkResult<LoginResponse> {
        return withContext(ioDispatcher) {
            com.example.data.remote.handleApi { loginService.login(LoginRequest(login, password)) }
        }
    }
}