package com.example.honkaihelper.login.data.models

import com.example.honkaihelper.profile.data.model.User

data class LoginResponse(
    val token: String,
    val user: User
)
