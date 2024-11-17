package com.example.core.di

import com.example.core.data.repository.HeroRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindHeroRepository(repository: HeroRepositoryImpl): com.example.domain.repository.hero.HeroRepository
}