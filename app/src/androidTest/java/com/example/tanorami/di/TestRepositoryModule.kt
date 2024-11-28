package com.example.tanorami.di

import com.example.tanorami.auth.login.domain.LoginRepository
import com.example.tanorami.auth.registration.data.RegistrationRepository
import com.example.tanorami.createteam.data.CreateTeamRepository
import com.example.tanorami.teams.data.TeamsFromUsersRepository
import dagger.Binds
import dagger.Module

//@Module
//interface TestRepositoryModule {
//
//    @Binds
//    fun bindFakeTeamsListRepository(repository: FakeTeamsFromUsersRepository): TeamsFromUsersRepository
//
//    @Binds
//    fun bindFakeCreateTeamRepository(repository: FakeCreateTeamRepository): CreateTeamRepository
//
//    @Binds
//    fun bindFakeLoginRepository(repository: FakeLoginRepository): LoginRepository
//
//    @Binds
//    fun bindFakeProfileRepository(repository: FakeProfileRepository): ProfileRepository
//
//    @Binds
//    fun bindFakeRegistrationRepository(repository: FakeRegistrationRepository): RegistrationRepository
//}