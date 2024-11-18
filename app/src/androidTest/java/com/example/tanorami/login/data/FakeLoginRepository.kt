package com.example.tanorami.login.data

import com.example.tanorami.auth.login.data.models.LoginResponse
import com.example.tanorami.auth.login.domain.LoginRepository
import com.example.tanorami.profile.data.model.UserResponse
import javax.inject.Inject

class FakeLoginRepository @Inject constructor(): LoginRepository {
    override suspend fun login(
        login: String,
        password: String
    ): com.example.data.remote.NetworkResult<LoginResponse> {
        return com.example.data.remote.NetworkResult.Success(
            LoginResponse(
                "",
                UserResponse("", "", emptyList())
            )
        )
    }
}