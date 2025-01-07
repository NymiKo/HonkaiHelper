package com.example.heroes_list.heroes.di

import com.example.domain.data_store.AppDataStore
import com.example.domain.usecase.hero.GetHeroesListWithBaseInfoUseCase

interface HeroesListComponentDependencies {
    val getUseCase: GetHeroesListWithBaseInfoUseCase
    val getAppDataStore: AppDataStore
}