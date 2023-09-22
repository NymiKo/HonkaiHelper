package com.example.honkaihelper.registration.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.registration.data.model.RegistrationRequest
import com.example.honkaihelper.registration.data.model.RegistrationResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val registrationService: RegistrationService
) : RegistrationRepository {
    override suspend fun register(
        login: String,
        password: String
    ): NetworkResult<RegistrationResponse> {
        return withContext(ioDispatcher) {
            handleApiTest {
                registrationService.registration(RegistrationRequest(login, password))
            }
        }
    }
}

suspend fun <T> handleApiTest(apiCall: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = apiCall()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(response.code())
        }
    } catch (e: UnknownHostException) {
        NetworkResult.Error(105)
    }
}