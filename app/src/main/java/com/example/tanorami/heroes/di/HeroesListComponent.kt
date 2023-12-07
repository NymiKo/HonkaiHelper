package com.example.tanorami.heroes.di

import com.example.tanorami.heroes.HeroesListFragment
import dagger.Subcomponent

@Subcomponent(modules = [HeroesListModule::class])
interface HeroesListComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HeroesListComponent
    }
    fun inject(fragment: HeroesListFragment)
}