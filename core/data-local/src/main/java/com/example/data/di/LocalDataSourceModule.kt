package com.example.data.di

import com.example.data.source.hero.HeroLocalDataSource
import com.example.data.source.hero.HeroLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun provideHeroLocalDataSource(heroLocalDataSourceImpl: HeroLocalDataSourceImpl): HeroLocalDataSource
}