package com.example.tanorami.profile.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.profile.data.model.User
import java.io.File
import javax.inject.Inject

class FakeProfileRepository @Inject constructor(): ProfileRepository {
    override suspend fun getProfile(): NetworkResult<User> {
        return NetworkResult.Success(User("", "", emptyList()))
    }

    override suspend fun loadAvatar(file: File): NetworkResult<Unit> {
        return NetworkResult.Success(Unit)
    }
}