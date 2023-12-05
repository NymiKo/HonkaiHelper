package com.example.honkaihelper.settings.di

import com.example.honkaihelper.settings.SettingsFragment
import dagger.Subcomponent

@Subcomponent(modules = [SettingsModule::class])
interface SettingsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsComponent
    }

    fun inject(fragment: SettingsFragment)
}