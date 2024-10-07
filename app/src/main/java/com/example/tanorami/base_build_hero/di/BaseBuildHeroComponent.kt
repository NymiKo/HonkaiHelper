package com.example.tanorami.base_build_hero.di

import com.example.tanorami.base_build_hero.ui.BaseBuildHeroFragment
import dagger.Subcomponent

@Subcomponent(modules = [BaseBuildHeroModule::class])
interface BaseBuildHeroComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): BaseBuildHeroComponent
    }

    fun inject(fragment: BaseBuildHeroFragment)
}