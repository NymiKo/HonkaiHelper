package com.example.tanorami.create_build_hero.data

import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser

interface CreateBuildHeroRepository {
    suspend fun getHero(idHero: Int): com.example.domain.repository.hero.model.HeroModel
    suspend fun saveBuild(buildHeroFromUser: BuildHeroFromUser): com.example.data.remote.NetworkResult<Boolean>
    suspend fun getBuild(idBuild: Long): com.example.data.remote.NetworkResult<FullBuildHeroFromUser>
    suspend fun deleteBuild(idBuild: Long): com.example.data.remote.NetworkResult<Boolean>
    suspend fun getWeapons(path: Int): List<com.example.domain.repository.equipment.Equipment>
    suspend fun getRelics(): List<com.example.domain.repository.equipment.Equipment>
    suspend fun getDecorations(): List<com.example.domain.repository.equipment.Equipment>
}