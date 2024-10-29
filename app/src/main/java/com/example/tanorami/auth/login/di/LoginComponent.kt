package com.example.tanorami.auth.login.di

import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }
}