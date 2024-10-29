package com.example.tanorami.send_feedback.data

import com.example.tanorami.core.data.NetworkResult


interface SendFeedbackRepository {

    suspend fun sendFeedback(message: String): NetworkResult<Boolean>
}