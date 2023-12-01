package com.example.honkaihelper.create_build_hero.data

import com.example.honkaihelper.create_build_hero.data.model.BuildHeroFromUser
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.viewing_users_build.data.model.FullBuildHeroFromUser

interface CreateBuildHeroRepository {
    suspend fun getHero(idHero: Int): Hero
    suspend fun saveBuild(buildHeroFromUser: BuildHeroFromUser): NetworkResult<Boolean>
    suspend fun getBuild(idBuild: Int): NetworkResult<FullBuildHeroFromUser>
    suspend fun deleteBuild(idBuild: Int): NetworkResult<Boolean>
}