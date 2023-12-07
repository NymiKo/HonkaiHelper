package com.example.tanorami.send_feedback.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.send_feedback.SendFeedbackViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SendFeedbackModule {

    @Binds
    @[IntoMap ViewModelKey(SendFeedbackViewModel::class)]
    fun bindViewModel(viewModel: SendFeedbackViewModel): ViewModel
}