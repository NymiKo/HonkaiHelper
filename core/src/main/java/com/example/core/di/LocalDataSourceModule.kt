package com.example.core.di

import com.example.core.data.source.local.heroes.HeroLocalDataSource
import com.example.core.data.source.local.heroes.HeroLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun provideHeroLocalDataSource(heroLocalDataSourceImpl: HeroLocalDataSourceImpl): HeroLocalDataSource
}