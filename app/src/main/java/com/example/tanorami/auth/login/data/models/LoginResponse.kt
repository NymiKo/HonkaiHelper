package com.example.tanorami.auth.login.data.models

import com.example.data.remote.api.profile.model.ProfileDto

data class LoginResponse(
    val token: String,
    val user: ProfileDto,
)
