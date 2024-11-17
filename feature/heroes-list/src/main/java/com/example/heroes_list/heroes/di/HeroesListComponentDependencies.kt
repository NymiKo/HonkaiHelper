package com.example.heroes_list.heroes.di

import com.example.core.data.source.local.data_store.AppDataStore
import com.example.domain.usecase.GetHeroesListWithBaseInfoUseCase

interface HeroesListComponentDependencies {
    val getUseCase: GetHeroesListWithBaseInfoUseCase
    val getAppDataStore: AppDataStore
}