package com.example.honkaihelper.registration.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.registration.data.model.RegistrationRequest
import com.example.honkaihelper.registration.data.model.RegistrationResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
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