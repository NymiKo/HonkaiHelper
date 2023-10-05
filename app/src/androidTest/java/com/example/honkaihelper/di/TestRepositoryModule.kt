package com.example.honkaihelper.di

import com.example.honkaihelper.createteam.data.CreateTeamRepository
import com.example.honkaihelper.createteam.data.FakeCreateTeamRepository
import com.example.honkaihelper.heroes.data.FakeHeroesListRepository
import com.example.honkaihelper.heroes.data.HeroesListRepository
import com.example.honkaihelper.login.data.FakeLoginRepository
import com.example.honkaihelper.login.data.LoginRepository
import com.example.honkaihelper.profile.data.FakeProfileRepository
import com.example.honkaihelper.profile.data.ProfileRepository
import com.example.honkaihelper.registration.data.FakeRegistrationRepository
import com.example.honkaihelper.registration.data.RegistrationRepository
import com.example.honkaihelper.teams.data.FakeTeamsListRepository
import com.example.honkaihelper.teams.data.TeamsListRepository
import dagger.Binds
import dagger.Module

@Module
interface TestRepositoryModule {

    @Binds
    fun bindFakeHeroesListRepository(repository: FakeHeroesListRepository): HeroesListRepository

    @Binds
    fun bindFakeTeamsListRepository(repository: FakeTeamsListRepository): TeamsListRepository

    @Binds
    fun bindFakeCreateTeamRepository(repository: FakeCreateTeamRepository): CreateTeamRepository

    @Binds
    fun bindFakeLoginRepository(repository: FakeLoginRepository): LoginRepository

    @Binds
    fun bindFakeProfileRepository(repository: FakeProfileRepository): ProfileRepository

    @Binds
    fun bindFakeRegistrationRepository(repository: FakeRegistrationRepository): RegistrationRepository
}