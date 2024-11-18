package com.example.tanorami.registration.data

import com.example.tanorami.auth.registration.data.RegistrationRepository
import javax.inject.Inject

class FakeRegistrationRepository @Inject constructor(): RegistrationRepository {
    override suspend fun register(
        login: String,
        password: String
    ): com.example.data.remote.NetworkResult<Unit> {
        return com.example.data.remote.NetworkResult.Success(Unit)
    }
}