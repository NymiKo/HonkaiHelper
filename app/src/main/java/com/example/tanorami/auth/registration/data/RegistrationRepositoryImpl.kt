package com.example.tanorami.auth.registration.data

import com.example.tanorami.auth.registration.data.model.RegistrationRequest
import com.example.tanorami.core.di.IODispatcher
import com.example.tanorami.core.network.NetworkResult
import com.example.tanorami.core.network.handleApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
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