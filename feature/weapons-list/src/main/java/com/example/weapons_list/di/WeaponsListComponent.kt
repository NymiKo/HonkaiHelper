package com.example.weapons_list.di

import com.example.di.scopes.FeatureScope
import com.example.weapons_list.presentation.WeaponsListViewModel
import dagger.Component

@FeatureScope
@Component(
    modules = [WeaponsListModule::class],
    dependencies = [WeaponsListComponentDependencies::class]
)
internal interface WeaponsListComponent {
    @Component.Factory
    interface Factory {
        fun create(
            weaponsListComponentDependencies: WeaponsListComponentDependencies,
        ): WeaponsListComponent
    }

    fun getViewModel(): WeaponsListViewModel
}