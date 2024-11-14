package com.example.core.di

import com.example.core.domain.usecase.GetHeroesListWithBaseInfoUseCase
import com.example.core.domain.usecase.impl.GetHeroesListWithBaseInfoUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindGetHeroesListWithBaseInfoUseCase(getHeroesListWithBaseInfoUseCase: GetHeroesListWithBaseInfoUseCaseImpl): GetHeroesListWithBaseInfoUseCase
}