package com.example.tanorami.auth.login.domain

import com.example.data.remote.util.handleApi
import com.example.domain.di.DispatcherIo
import com.example.domain.util.NetworkResult
import com.example.tanorami.auth.login.data.LoginService
import com.example.tanorami.auth.login.data.models.LoginRequest
import com.example.tanorami.auth.login.data.models.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val loginService: LoginService,
) : LoginRepository {
    override suspend fun login(
        login: String,
        password: String,
    ): NetworkResult<LoginResponse> {
        return withContext(ioDispatcher) {
            handleApi { loginService.login(LoginRequest(login, password)) }
        }
    }
}