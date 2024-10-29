package com.example.tanorami.auth.registration.di

import dagger.Subcomponent

@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }
}