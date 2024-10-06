package com.example.tanorami.info_about_relic.di

import com.example.tanorami.info_about_relic.ui.InfoAboutRelicFragment
import dagger.Subcomponent

@Subcomponent(modules = [RelicInfoModule::class])
interface RelicInfoComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RelicInfoComponent
    }

    fun inject(fragment: InfoAboutRelicFragment)
}