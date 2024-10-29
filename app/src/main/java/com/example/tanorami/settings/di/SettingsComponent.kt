package com.example.tanorami.settings.di

import dagger.Subcomponent

@Subcomponent(modules = [SettingsModule::class])
interface SettingsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsComponent
    }
}