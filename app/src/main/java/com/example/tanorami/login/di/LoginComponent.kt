package com.example.tanorami.login.di

import com.example.tanorami.login.LoginFragment
import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(fragment: LoginFragment)
}