package com.example.honkaihelper.registration.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject

@Module
interface RegistrationModule {

    @Binds
    @[IntoMap ViewModelKey(RegistrationViewModel::class)]
    fun bindViewModel(viewModel: RegistrationViewModel): ViewModel
}