package com.example.tanorami.di

import com.example.tanorami.createteam.data.CreateTeamRepository
import com.example.tanorami.createteam.data.FakeCreateTeamRepository
import com.example.tanorami.heroes.data.FakeHeroesListRepository
import com.example.tanorami.heroes.data.HeroesListRepository
import com.example.tanorami.login.data.FakeLoginRepository
import com.example.tanorami.login.data.LoginRepository
import com.example.tanorami.profile.data.FakeProfileRepository
import com.example.tanorami.profile.data.ProfileRepository
import com.example.tanorami.registration.data.FakeRegistrationRepository
import com.example.tanorami.registration.data.RegistrationRepository
import com.example.tanorami.teams.data.FakeTeamsListRepository
import com.example.tanorami.teams.data.TeamsListRepository
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