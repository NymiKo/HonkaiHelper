package com.example.honkaihelper.di.components

import dagger.Component

@Component
interface HeroesListFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(): HeroesListFragmentComponent
    }
    fun inject(fragment: HeroesListFragmentComponent)
}