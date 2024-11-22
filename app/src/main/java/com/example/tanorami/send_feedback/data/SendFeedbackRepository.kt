package com.example.tanorami.send_feedback.data

import com.example.data.remote.util.NetworkResult

interface SendFeedbackRepository {

    suspend fun sendFeedback(message: String): NetworkResult<Boolean>
}