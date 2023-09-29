package com.example.honkaihelper.profile.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.profile.data.model.User

interface ProfileRepository {
    suspend fun getProfile(): NetworkResult<User>
}