package com.example.tanorami.send_feedback.data

interface SendFeedbackRepository {

    suspend fun sendFeedback(message: String): com.example.data.remote.NetworkResult<Boolean>
}