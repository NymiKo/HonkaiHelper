package com.example.profile.di

import com.example.di.scopes.FeatureScope
import com.example.profile.presentation.ProfileViewModel
import dagger.Component

@FeatureScope
@Component(
    modules = [ProfileModule::class],
    dependencies = [ProfileComponentDependencies::class]
)
interface ProfileComponent {
    @Component.Factory
    interface Factory {
        fun create(
            profileComponentDependencies: ProfileComponentDependencies,
        ): ProfileComponent
    }

    fun getViewModel(): ProfileViewModel
}