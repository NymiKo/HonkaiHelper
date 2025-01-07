package com.example.teams_and_builds.di

import com.example.di.scopes.FeatureScope
import com.example.teams_and_builds.data.TeamsAndBuildsRepositoryImpl
import com.example.teams_and_builds.domain.TeamsAndBuildsRepository
import dagger.Binds
import dagger.Module

@Module
internal interface TeamsAndBuildsModule {
    @Binds
    @FeatureScope
    fun provideTeamsAndBuildsRepository(teamsAndBuildsRepositoryImpl: TeamsAndBuildsRepositoryImpl): TeamsAndBuildsRepository
}