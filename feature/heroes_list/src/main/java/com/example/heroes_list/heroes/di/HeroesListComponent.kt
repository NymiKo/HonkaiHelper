package com.example.heroes_list.heroes.di

import dagger.Subcomponent

@Subcomponent(modules = [HeroesListModule::class])
interface HeroesListComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HeroesListComponent
    }
}