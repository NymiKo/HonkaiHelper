package com.example.data.di

import com.example.data.source.hero.HeroLocalDataSource
import com.example.data.source.hero.HeroLocalDataSourceImpl
import com.example.data.source.weapon.WeaponLocalDataSource
import com.example.data.source.weapon.WeaponLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun bindHeroLocalDataSource(heroLocalDataSourceImpl: HeroLocalDataSourceImpl): HeroLocalDataSource

    @Binds
    @Singleton
    fun bindWeaponLocalDataSource(weaponLocalDataSourceImpl: WeaponLocalDataSourceImpl): WeaponLocalDataSource
}