package com.example.tanorami.create_build_hero.di

import com.example.tanorami.create_build_hero.ui.CreateBuildHeroFragment
import dagger.Subcomponent

@Subcomponent(modules = [CreateBuildHeroModule::class])
interface CreateBuildHeroComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CreateBuildHeroComponent
    }

    fun inject(fragment: CreateBuildHeroFragment)
}