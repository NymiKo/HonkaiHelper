package com.example.tanorami.profile.domain

import com.example.tanorami.core.data.NetworkResult
import com.example.tanorami.profile.data.model.User
import java.io.File

interface ProfileRepository {
    suspend fun getProfile(): NetworkResult<User>
    suspend fun loadAvatar(file: File): NetworkResult<Unit>
}