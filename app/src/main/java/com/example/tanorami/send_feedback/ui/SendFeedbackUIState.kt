package com.example.tanorami.send_feedback.ui

sealed class SendFeedbackUIState {
    object LOADING: SendFeedbackUIState()
    data class ERROR(val message: Int): SendFeedbackUIState()
    object SUCCESS: SendFeedbackUIState()
}