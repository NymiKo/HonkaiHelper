package com.example.tanorami.auth.registration.data

interface RegistrationRepository {
    suspend fun register(
        login: String,
        password: String
    ): com.example.data.remote.NetworkResult<Unit>
}