package com.example.data.remote.api.profile

import com.example.data.remote.api.profile.model.ProfileDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ProfileApi {
    @POST("profile.php")
    suspend fun getProfile(): Response<ProfileDto>

    @Multipart
    @POST("loadAvatar.php")
    suspend fun loadAvatar(@Part avatar: MultipartBody.Part): Response<Unit>
}