package com.example.honkaihelper.di

import com.example.honkaihelper.heroes.data.HeroesListRepository
import com.example.honkaihelper.heroes.data.HeroesListRepositoryImpl
import com.example.honkaihelper.teams.data.TeamsListRepository
import com.example.honkaihelper.teams.data.TeamsListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindHeroesListRepository(repository: HeroesListRepositoryImpl): HeroesListRepository

    @Binds
    fun bindTeamsListRepository(repository: TeamsListRepositoryImpl): TeamsListRepository
}