package com.example.tanorami.send_feedback.presentation

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.data.remote.util.NetworkResult
import com.example.strings.R
import com.example.tanorami.send_feedback.data.SendFeedbackRepository
import com.example.tanorami.send_feedback.presentation.models.SendFeedbackScreenEvents
import com.example.tanorami.send_feedback.presentation.models.SendFeedbackScreenSideEffects
import com.example.tanorami.send_feedback.presentation.models.SendFeedbackScreenUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SendFeedbackViewModel @Inject constructor(
    private val repository: SendFeedbackRepository
) : BaseViewModel<SendFeedbackScreenUiState, SendFeedbackScreenEvents, SendFeedbackScreenSideEffects>(
    initialState = SendFeedbackScreenUiState()
) {

    override fun onEvent(event: SendFeedbackScreenEvents) {
        when (event) {
            is SendFeedbackScreenEvents.MessageChanged -> uiState =
                uiState.copy(messageValue = event.newValue)

            SendFeedbackScreenEvents.OnBack -> sendSideEffect(SendFeedbackScreenSideEffects.OnBack)
            SendFeedbackScreenEvents.SendFeedbackClick -> if (uiState.messageValue.isNotEmpty()) sendFeedback()
        }
    }

    private fun sendFeedback() = viewModelScope.launch {
        uiState = uiState.copy(isSendingFeedback = true)
        when (val result = repository.sendFeedback(uiState.messageValue)) {
            is NetworkResult.Error -> {
                sendSideEffect(SendFeedbackScreenSideEffects.ShowToast(errorHandler(result.code)))
                uiState = uiState.copy(isSendingFeedback = false)
            }

            is NetworkResult.Success -> {
                sendSideEffect(SendFeedbackScreenSideEffects.ShowToast(R.string.feedback_has_been_sent))
                uiState = uiState.copy(isSendingFeedback = false, messageValue = "")
            }
        }
    }

    private fun errorHandler(errorCode: Int): Int {
        return when (errorCode) {
            105 -> R.string.check_your_internet_connection
            106 -> R.string.error
            else -> R.string.unknown_error
        }
    }
}