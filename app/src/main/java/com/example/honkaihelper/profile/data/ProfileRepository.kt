package com.example.honkaihelper.profile.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.profile.data.model.User
import com.example.honkaihelper.profile.data.model.UserResponse
import java.io.File

interface ProfileRepository {
    suspend fun getProfile(): NetworkResult<User>
    suspend fun loadAvatar(file: File): NetworkResult<Unit>
}