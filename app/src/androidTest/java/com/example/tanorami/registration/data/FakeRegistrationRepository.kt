package com.example.tanorami.registration.data

import com.example.core.network.NetworkResult
import com.example.tanorami.auth.registration.data.RegistrationRepository
import javax.inject.Inject

class FakeRegistrationRepository @Inject constructor(): RegistrationRepository {
    override suspend fun register(login: String, password: String): NetworkResult<Unit> {
        return NetworkResult.Success(Unit)
    }
}