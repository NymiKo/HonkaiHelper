package com.example.tanorami.send_feedback.data

import com.example.core.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendFeedbackRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val sendFeedbackService: SendFeedbackService
): SendFeedbackRepository {
    override suspend fun sendFeedback(message: String): com.example.data.remote.NetworkResult<Boolean> =
        withContext(ioDispatcher) {
            return@withContext com.example.data.remote.handleApi {
                sendFeedbackService.sendFeedback(
                    message
                )
            }
    }
}