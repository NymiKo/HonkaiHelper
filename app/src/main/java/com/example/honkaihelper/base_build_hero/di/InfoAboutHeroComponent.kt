package com.example.honkaihelper.base_build_hero.di

import com.example.honkaihelper.base_build_hero.InfoAboutHeroFragment
import dagger.Subcomponent

@Subcomponent(modules = [InfoAboutHeroModule::class])
interface InfoAboutHeroComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): InfoAboutHeroComponent
    }

    fun inject(fragment: InfoAboutHeroFragment)
}