package com.example.tanorami.send_feedback.presentation.models

import com.example.base.UiEffect

sealed interface SendFeedbackScreenSideEffects : UiEffect {
    class ShowToast(val message: Int): SendFeedbackScreenSideEffects
    data object OnBack: SendFeedbackScreenSideEffects
}