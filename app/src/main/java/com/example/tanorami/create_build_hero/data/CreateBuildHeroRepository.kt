package com.example.tanorami.create_build_hero.data

import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.create_build_hero.data.model.Equipment
import com.example.tanorami.heroes.data.model.Hero
import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser

interface CreateBuildHeroRepository {
    suspend fun getHero(idHero: Int): Hero
    suspend fun saveBuild(buildHeroFromUser: BuildHeroFromUser): NetworkResult<Boolean>
    suspend fun getBuild(idBuild: Long): NetworkResult<FullBuildHeroFromUser>
    suspend fun deleteBuild(idBuild: Long): NetworkResult<Boolean>
    suspend fun getWeapons(path: Int): List<Equipment>
    suspend fun getRelics(): List<Equipment>
    suspend fun getDecorations(): List<Equipment>
}