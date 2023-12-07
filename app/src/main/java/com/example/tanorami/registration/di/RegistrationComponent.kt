package com.example.tanorami.registration.di

import com.example.tanorami.registration.RegistrationFragment
import dagger.Subcomponent

@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }
    fun inject(fragment: RegistrationFragment)
}