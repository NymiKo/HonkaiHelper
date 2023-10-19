package com.example.honkaihelper.base_build_hero.di

import com.example.honkaihelper.base_build_hero.BaseBuildHeroFragment
import dagger.Subcomponent

@Subcomponent
interface BaseBuildHeroComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): BaseBuildHeroComponent
    }

    fun inject(fragment: BaseBuildHeroFragment)
}