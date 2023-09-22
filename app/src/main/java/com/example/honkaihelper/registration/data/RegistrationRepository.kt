package com.example.honkaihelper.registration.data

import com.example.honkaihelper.registration.data.model.RegistrationResponse
import com.example.honkaihelper.data.NetworkResult

interface RegistrationRepository {
    suspend fun register(login: String, password: String): NetworkResult<RegistrationResponse>
}