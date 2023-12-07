package com.example.tanorami.registration.data

import com.example.tanorami.registration.data.model.RegistrationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {
    @POST("/registration.php")
    suspend fun registration(@Body registrationRequest: RegistrationRequest): Response<Unit>
}