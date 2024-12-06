package com.example.data.di

import com.example.data.repository.HeroRepositoryImpl
import com.example.domain.repository.hero.HeroRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface RepositoryModule {
    @Singleton
    @Binds
    fun bindHeroRepository(repository: HeroRepositoryImpl): HeroRepository
}