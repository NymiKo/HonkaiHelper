package com.example.tanorami.profile.domain

import com.example.tanorami.core.data.NetworkResult
import com.example.tanorami.profile.data.model.User
import kotlinx.coroutines.flow.StateFlow
import java.io.File

interface ProfileRepository {
    val profileFlow: StateFlow<NetworkResult<User>?>

    suspend fun getProfile()
    suspend fun loadAvatar(file: File): NetworkResult<Unit>
}