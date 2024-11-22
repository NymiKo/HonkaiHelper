package com.example.tanorami.auth.registration.data

import com.example.data.remote.util.NetworkResult

interface RegistrationRepository {
    suspend fun register(
        login: String,
        password: String,
    ): NetworkResult<Unit>
}