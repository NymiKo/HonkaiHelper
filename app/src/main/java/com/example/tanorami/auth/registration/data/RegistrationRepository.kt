package com.example.tanorami.auth.registration.data

import com.example.tanorami.core.network.NetworkResult


interface RegistrationRepository {
    suspend fun register(login: String, password: String): NetworkResult<Unit>
}