package com.example.honkaihelper.di

import com.example.honkaihelper.createteam.data.CreateTeamRepository
import com.example.honkaihelper.createteam.data.CreateTeamRepositoryImpl
import com.example.honkaihelper.heroes.data.HeroesListRepository
import com.example.honkaihelper.heroes.data.HeroesListRepositoryImpl
import com.example.honkaihelper.login.data.LoginRepository
import com.example.honkaihelper.login.data.LoginRepositoryImpl
import com.example.honkaihelper.registration.data.RegistrationRepository
import com.example.honkaihelper.registration.data.RegistrationRepositoryImpl
import com.example.honkaihelper.teams.data.TeamsListRepository
import com.example.honkaihelper.teams.data.TeamsListRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindHeroesListRepository(repository: HeroesListRepositoryImpl): HeroesListRepository

    @Singleton
    @Binds
    fun bindTeamsListRepository(repository: TeamsListRepositoryImpl): TeamsListRepository

    @Singleton
    @Binds
    fun bindCreateTeamRepository(repository: CreateTeamRepositoryImpl): CreateTeamRepository

    @Singleton
    @Binds
    fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository

    @Singleton
    @Binds
    fun bindRegistrationRepository(repository: RegistrationRepositoryImpl): RegistrationRepository
}