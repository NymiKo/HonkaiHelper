package com.example.data.di

import com.example.data.source.hero.HeroRemoteDataSource
import com.example.data.source.hero.HeroRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface RemoteDataSourceModule {

    @Binds
    @Singleton
    fun bindHeroRemoteDataSource(heroRemoteDataSourceImpl: HeroRemoteDataSourceImpl): HeroRemoteDataSource
}