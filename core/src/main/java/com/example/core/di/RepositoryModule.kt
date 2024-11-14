package com.example.core.di

import com.example.core.data.repository.HeroRepositoryImpl
import com.example.core.domain.repository.hero.HeroRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindHeroRepository(repository: HeroRepositoryImpl): HeroRepository
}