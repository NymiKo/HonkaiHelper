package com.example.data.di

import com.example.data.source.hero.HeroRemoteDataSource
import com.example.data.source.hero.HeroRemoteDataSourceImpl
import com.example.data.source.hero_build.HeroBuildsFromUsersRemoteDataSource
import com.example.data.source.hero_build.HeroBuildsFromUsersRemoteDataSourceImpl
import com.example.data.source.stat.StatRemoteDataSource
import com.example.data.source.stat.StatRemoteDataSourceImpl
import com.example.data.source.team.TeamsFromUsersRemoteDataSource
import com.example.data.source.team.TeamsFromUsersRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface RemoteDataSourceModule {

    @Binds
    @Singleton
    fun bindHeroRemoteDataSource(heroRemoteDataSourceImpl: HeroRemoteDataSourceImpl): HeroRemoteDataSource

    @Binds
    @Singleton
    fun bindHeroBuildsFromUserRemoteDataSource(heroBuildsFromUsersRemoteDataSourceImpl: HeroBuildsFromUsersRemoteDataSourceImpl): HeroBuildsFromUsersRemoteDataSource

    @Binds
    @Singleton
    fun bindTeamsFromUsersRemoteDataSource(teamsFromUsersRemoteDataSourceImpl: TeamsFromUsersRemoteDataSourceImpl): TeamsFromUsersRemoteDataSource

    @Binds
    @Singleton
    fun bindStatRemoteDataSource(statRemoteDataSourceImpl: StatRemoteDataSourceImpl): StatRemoteDataSource
}