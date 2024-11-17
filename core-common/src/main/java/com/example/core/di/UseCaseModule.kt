package com.example.core.di

import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindGetHeroesListWithBaseInfoUseCase(getHeroesListWithBaseInfoUseCase: com.example.domain.usecase.impl.GetHeroesListWithBaseInfoUseCaseImpl): com.example.domain.usecase.GetHeroesListWithBaseInfoUseCase
}