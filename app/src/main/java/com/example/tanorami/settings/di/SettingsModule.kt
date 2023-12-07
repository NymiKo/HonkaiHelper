package com.example.tanorami.settings.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SettingsModule {

    @Binds
    @[IntoMap ViewModelKey(SettingsViewModel::class)]
    fun bindViewModel(viewModel: SettingsViewModel): ViewModel
}