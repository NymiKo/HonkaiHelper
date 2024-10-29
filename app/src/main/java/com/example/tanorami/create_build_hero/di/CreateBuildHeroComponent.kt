package com.example.tanorami.create_build_hero.di

import dagger.Subcomponent

@Subcomponent(modules = [CreateBuildHeroModule::class])
interface CreateBuildHeroComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CreateBuildHeroComponent
    }
}