package com.example.honkaihelper.send_feedback.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendFeedbackRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val sendFeedbackService: SendFeedbackService
): SendFeedbackRepository {
    override suspend fun sendFeedback(message: String): NetworkResult<Boolean> = withContext(ioDispatcher) {
        return@withContext handleApi { sendFeedbackService.sendFeedback(message) }
    }
}