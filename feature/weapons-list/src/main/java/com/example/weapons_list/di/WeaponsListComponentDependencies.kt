package com.example.weapons_list.di

import com.example.data.source.weapon.WeaponLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher

interface WeaponsListComponentDependencies {
    val getWeaponLocalDataSource: WeaponLocalDataSource
    val getIoDispatcher: CoroutineDispatcher
}