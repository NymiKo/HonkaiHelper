package com.example.tanorami.auth.registration.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.auth.registration.presentation.RegistrationViewModel
import com.example.tanorami.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RegistrationModule {

    @Binds
    @[IntoMap ViewModelKey(RegistrationViewModel::class)]
    fun bindViewModel(viewModel: RegistrationViewModel): ViewModel
}