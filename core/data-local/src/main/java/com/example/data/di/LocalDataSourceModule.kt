package com.example.data.di

import com.example.data.source.additional_stat.AdditionalStatLocalDataSource
import com.example.data.source.additional_stat.AdditionalStatLocalDataSourceImpl
import com.example.data.source.decoration.DecorationLocalDataSource
import com.example.data.source.decoration.DecorationLocalDataSourceImpl
import com.example.data.source.hero.HeroLocalDataSource
import com.example.data.source.hero.HeroLocalDataSourceImpl
import com.example.data.source.optimal_stat_hero.OptimalStatLocalDataSource
import com.example.data.source.optimal_stat_hero.OptimalStatLocalDataSourceImpl
import com.example.data.source.relic.RelicLocalDataSource
import com.example.data.source.relic.RelicLocalDataSourceImpl
import com.example.data.source.stat.StatLocalDataSource
import com.example.data.source.stat.StatLocalDataSourceImpl
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

    @Binds
    @Singleton
    fun bindStatLocalDataSource(statLocalDataSourceImpl: StatLocalDataSourceImpl): StatLocalDataSource

    @Binds
    @Singleton
    fun bindAdditionalStatLocalDataSource(additionalStatLocalDataSourceImpl: AdditionalStatLocalDataSourceImpl): AdditionalStatLocalDataSource

    @Binds
    @Singleton
    fun bindOptimalStatLocalDataSource(optimalStatLocalDataSourceImpl: OptimalStatLocalDataSourceImpl): OptimalStatLocalDataSource
}