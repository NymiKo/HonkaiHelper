package com.example.tanorami.auth.login.data.models

import com.example.tanorami.profile.data.model.UserResponse

data class LoginResponse(
    val token: String,
    val user: UserResponse
)
