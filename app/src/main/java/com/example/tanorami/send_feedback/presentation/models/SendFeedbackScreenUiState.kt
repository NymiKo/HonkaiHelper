package com.example.tanorami.send_feedback.presentation.models

import com.example.tanorami.base.UiState

data class SendFeedbackScreenUiState(
    val isSendingFeedback: Boolean = false,
    val messageValue: String = "",
) : UiState
