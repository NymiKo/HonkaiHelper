package com.example.honkaihelper.create_build_hero.di

import com.example.honkaihelper.create_build_hero.CreateBuildHeroFragment
import dagger.Subcomponent

@Subcomponent(modules = [CreateBuildHeroModule::class])
interface CreateBuildHeroComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): CreateBuildHeroComponent
    }

    fun inject(fragment: CreateBuildHeroFragment)
}