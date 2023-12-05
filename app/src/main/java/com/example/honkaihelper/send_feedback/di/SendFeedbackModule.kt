package com.example.honkaihelper.send_feedback.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.send_feedback.SendFeedbackViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SendFeedbackModule {

    @Binds
    @[IntoMap ViewModelKey(SendFeedbackViewModel::class)]
    fun bindViewModel(viewModel: SendFeedbackViewModel): ViewModel
}