package com.example.honkaihelper.settings.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SettingsModule {

    @Binds
    @[IntoMap ViewModelKey(SettingsViewModel::class)]
    fun bindViewModel(viewModel: SettingsViewModel): ViewModel
}