package com.example.honkaihelper.registration.data

import com.example.honkaihelper.data.NetworkResult
import javax.inject.Inject

class FakeRegistrationRepository @Inject constructor(): RegistrationRepository {
    override suspend fun register(login: String, password: String): NetworkResult<Unit> {
        return NetworkResult.Success(Unit)
    }
}