package com.example.honkaihelper.profile.data

import com.example.honkaihelper.profile.data.model.User
import retrofit2.Response
import retrofit2.http.POST

interface ProfileService {
    @POST("profile.php")
    suspend fun getProfile(): Response<User>
}