package com.example.honkaihelper.profile.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.profile.data.model.User
import com.example.honkaihelper.profile.data.model.UserResponse
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