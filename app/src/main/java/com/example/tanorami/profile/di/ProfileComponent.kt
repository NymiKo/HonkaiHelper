package com.example.tanorami.profile.di

import dagger.Subcomponent

@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ProfileComponent
    }
}