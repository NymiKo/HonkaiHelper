package com.example.tanorami.send_feedback.data

import com.example.domain.util.NetworkResult

interface SendFeedbackRepository {

    suspend fun sendFeedback(message: String): NetworkResult<Boolean>
}