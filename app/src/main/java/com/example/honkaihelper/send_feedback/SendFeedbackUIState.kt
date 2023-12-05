package com.example.honkaihelper.send_feedback

sealed class SendFeedbackUIState {
    object LOADING: SendFeedbackUIState()
    data class ERROR(val message: Int): SendFeedbackUIState()
    object SUCCESS: SendFeedbackUIState()
}