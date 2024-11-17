package com.example.core.di

import com.example.core.data.source.local.hero.HeroLocalDataSource
import com.example.core.data.source.local.hero.HeroLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun provideHeroLocalDataSource(heroLocalDataSourceImpl: HeroLocalDataSourceImpl): HeroLocalDataSource
}