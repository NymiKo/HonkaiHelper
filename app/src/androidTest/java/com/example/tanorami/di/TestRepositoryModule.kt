package com.example.tanorami.di

import com.example.tanorami.auth.login.domain.LoginRepository
import com.example.tanorami.auth.registration.data.RegistrationRepository
import com.example.tanorami.createteam.data.CreateTeamRepository
import com.example.tanorami.createteam.data.FakeCreateTeamRepository
import com.example.tanorami.heroes.data.FakeHeroesListRepository
import com.example.tanorami.heroes.data.HeroesListRepository
import com.example.tanorami.login.data.FakeLoginRepository
import com.example.tanorami.profile.data.FakeProfileRepository
import com.example.tanorami.profile.domain.ProfileRepository
import com.example.tanorami.registration.data.FakeRegistrationRepository
import com.example.tanorami.teams.data.FakeTeamsFromUsersRepository
import com.example.tanorami.teams.data.TeamsFromUsersRepository
import dagger.Binds
import dagger.Module

@Module
interface TestRepositoryModule {

    @Binds
    fun bindFakeHeroesListRepository(repository: FakeHeroesListRepository): HeroesListRepository

    @Binds
    fun bindFakeTeamsListRepository(repository: FakeTeamsFromUsersRepository): TeamsFromUsersRepository

    @Binds
    fun bindFakeCreateTeamRepository(repository: FakeCreateTeamRepository): CreateTeamRepository

    @Binds
    fun bindFakeLoginRepository(repository: FakeLoginRepository): LoginRepository

    @Binds
    fun bindFakeProfileRepository(repository: FakeProfileRepository): ProfileRepository

    @Binds
    fun bindFakeRegistrationRepository(repository: FakeRegistrationRepository): RegistrationRepository
}