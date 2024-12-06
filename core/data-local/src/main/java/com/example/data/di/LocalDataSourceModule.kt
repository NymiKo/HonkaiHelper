package com.example.data.di

import com.example.data.source.decoration.DecorationLocalDataSource
import com.example.data.source.decoration.DecorationLocalDataSourceImpl
import com.example.data.source.hero.HeroLocalDataSource
import com.example.data.source.hero.HeroLocalDataSourceImpl
import com.example.data.source.relic.RelicLocalDataSource
import com.example.data.source.relic.RelicLocalDataSourceImpl
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

    @Binds
    @Singleton
    fun bindRelicLocalDataSource(relicLocalDataSourceImpl: RelicLocalDataSourceImpl): RelicLocalDataSource

    @Binds
    @Singleton
    fun bindDecorationLocalDataSource(decorationLocalDataSourceImpl: DecorationLocalDataSourceImpl): DecorationLocalDataSource
}