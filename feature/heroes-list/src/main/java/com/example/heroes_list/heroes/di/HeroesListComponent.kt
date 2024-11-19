package com.example.heroes_list.heroes.di

import com.example.di.scopes.FeatureScope
import com.example.heroes_list.heroes.presentation.HeroesListViewModel
import dagger.Component

@FeatureScope
@Component(
    modules = [HeroesListModule::class],
    dependencies = [HeroesListComponentDependencies::class]
)
interface HeroesListComponent {
    fun getViewModel(): HeroesListViewModel
}