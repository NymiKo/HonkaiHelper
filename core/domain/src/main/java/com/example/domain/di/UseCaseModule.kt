package com.example.domain.di

import com.example.domain.usecase.GetHeroesListWithBaseInfoUseCase
import com.example.domain.usecase.impl.GetHeroesListWithBaseInfoUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun bindGetHeroesListWithBaseInfoUseCase(getHeroesListWithBaseInfoUseCase: GetHeroesListWithBaseInfoUseCaseImpl): GetHeroesListWithBaseInfoUseCase
}