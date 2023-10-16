package com.example.honkaihelper.builds_hero.di

import com.example.honkaihelper.builds_hero.BuildsHeroListFragment
import dagger.Subcomponent

@Subcomponent(modules = [BuildsHeroListModule::class])
interface BuildsHeroListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BuildsHeroListComponent
    }

    fun inject(fragment: BuildsHeroListFragment)
}