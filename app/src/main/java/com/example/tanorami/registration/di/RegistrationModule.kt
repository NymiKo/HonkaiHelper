package com.example.tanorami.registration.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RegistrationModule {

    @Binds
    @[IntoMap ViewModelKey(RegistrationViewModel::class)]
    fun bindViewModel(viewModel: RegistrationViewModel): ViewModel
}