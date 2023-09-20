package com.example.honkaihelper.registration.data

import com.example.honkaihelper.registration.data.model.RegistrationResponse

interface RegistrationRepository {
    suspend fun register(login: String, password: String): Result<RegistrationResponse>
}