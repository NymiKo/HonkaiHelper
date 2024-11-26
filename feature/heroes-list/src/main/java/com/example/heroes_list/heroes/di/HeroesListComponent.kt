package com.example.heroes_list.heroes.di

import com.example.di.scopes.FeatureScope
import com.example.heroes_list.heroes.presentation.HeroesListViewModel
import dagger.Component

@FeatureScope
@Component(
    modules = [HeroesListModule::class],
    dependencies = [HeroesListComponentDependencies::class]
)
internal interface HeroesListComponent {
    fun getViewModel(): HeroesListViewModel
}