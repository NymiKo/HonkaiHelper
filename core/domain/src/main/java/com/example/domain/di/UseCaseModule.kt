package com.example.domain.di

import com.example.domain.usecase.hero.GetHeroesListWithBaseInfoUseCase
import com.example.domain.usecase.hero.impl.GetHeroesListWithBaseInfoUseCaseImpl
import com.example.domain.usecase.profile.GetProfileUseCase
import com.example.domain.usecase.profile.LogoutAccountUseCase
import com.example.domain.usecase.profile.impl.GetProfileUseCaseImpl
import com.example.domain.usecase.profile.impl.LogoutAccountUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal interface UseCaseModule {
    @Binds
    fun bindGetHeroesListWithBaseInfoUseCase(getHeroesListWithBaseInfoUseCase: GetHeroesListWithBaseInfoUseCaseImpl): GetHeroesListWithBaseInfoUseCase

    @Binds
    fun bindGetProfileUseCase(getProfileUseCase: GetProfileUseCaseImpl): GetProfileUseCase

    @Binds
    fun bindLogoutAccountUseCase(logoutAccountUseCase: LogoutAccountUseCaseImpl): LogoutAccountUseCase
}