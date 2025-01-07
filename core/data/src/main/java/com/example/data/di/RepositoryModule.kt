package com.example.data.di

import com.example.data.repository.HeroRepositoryImpl
import com.example.data.repository.ProfileRepositoryImpl
import com.example.domain.repository.hero.HeroRepository
import com.example.domain.repository.profile.ProfileRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface RepositoryModule {
    @Singleton
    @Binds
    fun bindHeroRepository(repository: HeroRepositoryImpl): HeroRepository

    @Singleton
    @Binds
    fun bindProfileRepository(repository: ProfileRepositoryImpl): ProfileRepository
}