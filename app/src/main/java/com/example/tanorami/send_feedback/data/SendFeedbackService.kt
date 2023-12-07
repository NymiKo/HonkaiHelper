package com.example.tanorami.send_feedback.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SendFeedbackService {

    @GET("/sendFeedback.php")
    suspend fun sendFeedback(@Query("message") message: String): Response<Boolean>
}