package com.example.tanorami.create_build_hero.data

import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
import com.example.core.domain.repository.equipment.Equipment
import com.example.core.domain.repository.hero.model.HeroModel
import com.example.core.network.NetworkResult
import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser

interface CreateBuildHeroRepository {
    suspend fun getHero(idHero: Int): HeroModel
    suspend fun saveBuild(buildHeroFromUser: BuildHeroFromUser): NetworkResult<Boolean>
    suspend fun getBuild(idBuild: Long): NetworkResult<FullBuildHeroFromUser>
    suspend fun deleteBuild(idBuild: Long): NetworkResult<Boolean>
    suspend fun getWeapons(path: Int): List<Equipment>
    suspend fun getRelics(): List<Equipment>
    suspend fun getDecorations(): List<Equipment>
}