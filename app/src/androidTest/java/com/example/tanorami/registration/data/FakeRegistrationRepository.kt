package com.example.tanorami.registration.data

import com.example.tanorami.auth.registration.data.RegistrationRepository
import com.example.tanorami.core.data.NetworkResult
import javax.inject.Inject

class FakeRegistrationRepository @Inject constructor(): RegistrationRepository {
    override suspend fun register(login: String, password: String): NetworkResult<Unit> {
        return NetworkResult.Success(Unit)
    }
}