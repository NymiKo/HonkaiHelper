package com.example.tanorami.auth.registration.data.model

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String
)
