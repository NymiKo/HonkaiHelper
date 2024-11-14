package com.example.tanorami.settings.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.tanorami.settings.presentation.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SettingsModule {

    @Binds
    @[IntoMap ViewModelKey(SettingsViewModel::class)]
    fun bindViewModel(viewModel: SettingsViewModel): ViewModel
}