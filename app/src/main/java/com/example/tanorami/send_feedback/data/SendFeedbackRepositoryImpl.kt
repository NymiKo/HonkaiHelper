package com.example.tanorami.send_feedback.data

import com.example.data.remote.util.handleApi
import com.example.domain.di.DispatcherIo
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendFeedbackRepositoryImpl @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val sendFeedbackService: SendFeedbackService,
): SendFeedbackRepository {
    override suspend fun sendFeedback(message: String): NetworkResult<Boolean> =
        withContext(ioDispatcher) {
            return@withContext handleApi {
                sendFeedbackService.sendFeedback(
                    message
                )
            }
    }
}