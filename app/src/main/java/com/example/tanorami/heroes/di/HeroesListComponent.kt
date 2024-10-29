package com.example.tanorami.heroes.di

import dagger.Subcomponent

@Subcomponent(modules = [HeroesListModule::class])
interface HeroesListComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HeroesListComponent
    }
}