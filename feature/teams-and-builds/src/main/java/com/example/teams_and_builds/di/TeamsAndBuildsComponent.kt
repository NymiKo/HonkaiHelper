package com.example.teams_and_builds.di

import com.example.di.scopes.FeatureScope
import com.example.teams_and_builds.presentation.TeamsAndBuildsViewModel
import dagger.Component

@FeatureScope
@Component(
    modules = [TeamsAndBuildsModule::class],
    dependencies = [TeamsAndBuildsComponentDependencies::class]
)
internal interface TeamsAndBuildsComponent {
    @Component.Factory
    interface Factory {
        fun create(
            teamsAndBuildsComponentDependencies: TeamsAndBuildsComponentDependencies,
        ): TeamsAndBuildsComponent
    }

    fun getViewModel(): TeamsAndBuildsViewModel
}