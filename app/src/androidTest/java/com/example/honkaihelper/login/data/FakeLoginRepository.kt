package com.example.honkaihelper.login.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.login.data.models.LoginResponse
import com.example.honkaihelper.profile.data.model.User
import javax.inject.Inject

class FakeLoginRepository @Inject constructor(): LoginRepository {
    override suspend fun login(login: String, password: String): NetworkResult<LoginResponse> {
        return NetworkResult.Success(LoginResponse("", User("", "", emptyList())))
    }
}