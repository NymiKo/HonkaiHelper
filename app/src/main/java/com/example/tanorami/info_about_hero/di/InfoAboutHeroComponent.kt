package com.example.tanorami.info_about_hero.di

import dagger.Subcomponent

@Subcomponent(modules = [InfoAboutHeroModule::class])
interface InfoAboutHeroComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): InfoAboutHeroComponent
    }
}