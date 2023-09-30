package com.example.honkaihelper.profile.data

import com.example.honkaihelper.profile.data.model.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ProfileService {
    @POST("profile.php")
    suspend fun getProfile(): Response<User>

    @Multipart
    @POST("loadAvatar.php")
    suspend fun loadAvatar(@Part avatar: MultipartBody.Part): Response<Unit>
}