package com.example.tanorami.info_about_relic.di

import com.example.tanorami.info_about_relic.ui.InfoAboutRelicFragment
import dagger.Subcomponent

@Subcomponent(modules = [InfoAboutRelicModule::class])
interface InfoAboutRelicComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): InfoAboutRelicComponent
    }

    fun inject(fragment: InfoAboutRelicFragment)
}