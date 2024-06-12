package com.example.tanorami.auth.registration.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.auth.registration.data.model.RegistrationRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val registrationService: RegistrationService
) : RegistrationRepository {
    override suspend fun register(
        login: String,
        password: String
    ): NetworkResult<Unit> {
        return withContext(ioDispatcher) {
            handleApi {
                registrationService.registration(RegistrationRequest(login, password))
            }
        }
    }
}