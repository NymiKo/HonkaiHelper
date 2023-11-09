package com.example.honkaihelper.relic.di

import com.example.honkaihelper.relic.RelicInfoFragment
import dagger.Subcomponent

@Subcomponent(modules = [RelicInfoModule::class])
interface RelicInfoComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RelicInfoComponent
    }

    fun inject(fragment: RelicInfoFragment)
}