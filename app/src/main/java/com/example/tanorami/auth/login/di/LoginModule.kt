package com.example.tanorami.auth.login.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.tanorami.auth.login.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginModule {
    @Binds
    @[IntoMap ViewModelKey(LoginViewModel::class)]
    fun bindViewModel(viewModel: LoginViewModel): ViewModel
}