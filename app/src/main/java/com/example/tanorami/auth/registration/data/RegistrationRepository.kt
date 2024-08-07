package com.example.tanorami.auth.registration.data

import com.example.tanorami.data.NetworkResult

interface RegistrationRepository {
    suspend fun register(login: String, password: String): NetworkResult<Unit>
}