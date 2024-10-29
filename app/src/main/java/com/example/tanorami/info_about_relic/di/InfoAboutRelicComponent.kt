package com.example.tanorami.info_about_relic.di

import dagger.Subcomponent

@Subcomponent(modules = [InfoAboutRelicModule::class])
interface InfoAboutRelicComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): InfoAboutRelicComponent
    }
}