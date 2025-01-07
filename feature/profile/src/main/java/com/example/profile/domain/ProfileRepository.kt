package com.example.profile.domain

import com.example.domain.util.NetworkResult
import java.io.File

interface ProfileRepository {
    suspend fun loadAvatar(file: File): NetworkResult<Unit>
}