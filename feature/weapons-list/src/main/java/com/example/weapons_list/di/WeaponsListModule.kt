package com.example.weapons_list.di

import com.example.data.source.weapon.WeaponLocalDataSource
import com.example.weapons_list.data.WeaponsListRepositoryImpl
import com.example.weapons_list.domain.WeaponsListRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
internal object WeaponsListModule {
    @Provides
    fun provideWeaponsListRepository(
        weaponLocalDataSource: WeaponLocalDataSource,
        ioDispatcher: CoroutineDispatcher,
    ): WeaponsListRepository = WeaponsListRepositoryImpl(weaponLocalDataSource, ioDispatcher)
}