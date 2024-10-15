package com.example.tanorami.send_feedback.presentation.models

import com.example.tanorami.base.UiEvent

sealed interface SendFeedbackScreenEvents : UiEvent {
    class MessageChanged(val newValue: String) : SendFeedbackScreenEvents
    data object SendFeedbackClick : SendFeedbackScreenEvents
    data object OnBack : SendFeedbackScreenEvents
}