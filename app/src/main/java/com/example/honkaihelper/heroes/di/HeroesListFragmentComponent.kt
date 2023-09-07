package com.example.honkaihelper.heroes.di

import dagger.Component

@Component
interface HeroesListFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(): HeroesListFragmentComponent
    }
    fun inject(fragment: HeroesListFragmentComponent)
}