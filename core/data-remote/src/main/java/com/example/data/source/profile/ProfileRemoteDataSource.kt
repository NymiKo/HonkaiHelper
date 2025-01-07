package com.example.data.source.profile

import com.example.data.remote.api.profile.model.ProfileDto
import com.example.domain.util.NetworkResult
import okhttp3.MultipartBody

interface ProfileRemoteDataSource {
    suspend fun getProfile(): NetworkResult<ProfileDto>
    suspend fun loadAvatar(avatar: MultipartBody.Part): NetworkResult<Unit>
}