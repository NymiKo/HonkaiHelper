package com.example.honkaihelper.send_feedback.di

import com.example.honkaihelper.send_feedback.SendFeedbackFragment
import dagger.Subcomponent

@Subcomponent(modules = [SendFeedbackModule::class])
interface SendFeedbackComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SendFeedbackComponent
    }

    fun inject(fragment: SendFeedbackFragment)
}