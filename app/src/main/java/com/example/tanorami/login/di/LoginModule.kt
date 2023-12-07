package com.example.tanorami.login.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginModule {
    @Binds
    @[IntoMap ViewModelKey(LoginViewModel::class)]
    fun bindViewModel(viewModel: LoginViewModel): ViewModel
}