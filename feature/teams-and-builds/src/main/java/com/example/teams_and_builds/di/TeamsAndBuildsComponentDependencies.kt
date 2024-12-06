package com.example.teams_and_builds.di

import com.example.data.source.decoration.DecorationLocalDataSource
import com.example.data.source.hero.HeroLocalDataSource
import com.example.data.source.hero_build.HeroBuildsFromUsersRemoteDataSource
import com.example.data.source.relic.RelicLocalDataSource
import com.example.data.source.team.TeamsFromUsersRemoteDataSource
import com.example.data.source.weapon.WeaponLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher

interface TeamsAndBuildsComponentDependencies {
    val getHeroBuildsFromUsersRemoteDataSource: HeroBuildsFromUsersRemoteDataSource
    val getTeamsFromUsersRemoteDataSource: TeamsFromUsersRemoteDataSource
    val getHeroLocalDataSource: HeroLocalDataSource
    val getWeaponLocalDataSource: WeaponLocalDataSource
    val getRelicLocalDataSource: RelicLocalDataSource
    val getDecorationLocalDataSource: DecorationLocalDataSource
    val getIoDispatcher: CoroutineDispatcher
}