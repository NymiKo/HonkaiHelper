package com.example.tanorami.auth.registration.di

import com.example.tanorami.auth.registration.ui.RegistrationFragment
import dagger.Subcomponent

@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }
    fun inject(fragment: RegistrationFragment)
}