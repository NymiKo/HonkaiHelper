package com.example.tanorami.send_feedback.di

import dagger.Subcomponent

@Subcomponent(modules = [SendFeedbackModule::class])
interface SendFeedbackComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SendFeedbackComponent
    }
}