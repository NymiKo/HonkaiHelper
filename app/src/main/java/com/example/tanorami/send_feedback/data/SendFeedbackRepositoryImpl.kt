package com.example.tanorami.send_feedback.data

import com.example.core.di.IODispatcher
import com.example.core.network.NetworkResult
import com.example.core.network.handleApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendFeedbackRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val sendFeedbackService: SendFeedbackService
): SendFeedbackRepository {
    override suspend fun sendFeedback(message: String): NetworkResult<Boolean> = withContext(ioDispatcher) {
        return@withContext handleApi { sendFeedbackService.sendFeedback(message) }
    }
}