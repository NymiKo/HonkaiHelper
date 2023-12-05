package com.example.honkaihelper.send_feedback.data

import com.example.honkaihelper.data.NetworkResult

interface SendFeedbackRepository {

    suspend fun sendFeedback(message: String): NetworkResult<Boolean>
}