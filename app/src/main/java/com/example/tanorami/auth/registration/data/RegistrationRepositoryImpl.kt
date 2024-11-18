package com.example.tanorami.auth.registration.data

import com.example.core.di.IODispatcher
import com.example.tanorami.auth.registration.data.model.RegistrationRequest
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
    ): com.example.data.remote.NetworkResult<Unit> {
        return withContext(ioDispatcher) {
            com.example.data.remote.handleApi {
                registrationService.registration(RegistrationRequest(login, password))
            }
        }
    }
}