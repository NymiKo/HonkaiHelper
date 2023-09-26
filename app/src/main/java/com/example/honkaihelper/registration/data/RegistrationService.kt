package com.example.honkaihelper.registration.data

import com.example.honkaihelper.registration.data.model.RegistrationRequest
import com.example.honkaihelper.registration.data.model.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {
    @POST("/registration.php")
    suspend fun registration(@Body registrationRequest: RegistrationRequest): Response<Unit>
}