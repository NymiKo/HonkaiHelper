package com.example.tanorami.profile.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.profile.data.model.User
import java.io.File

interface ProfileRepository {
    suspend fun getProfile(): NetworkResult<User>
    suspend fun loadAvatar(file: File): NetworkResult<Unit>
}