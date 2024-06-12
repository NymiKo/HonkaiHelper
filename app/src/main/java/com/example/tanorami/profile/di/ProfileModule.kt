package com.example.tanorami.profile.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.profile.presentation.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProfileModule {
    @Binds
    @[IntoMap ViewModelKey(ProfileViewModel::class)]
    fun bindViewModel(viewModel: ProfileViewModel): ViewModel
}