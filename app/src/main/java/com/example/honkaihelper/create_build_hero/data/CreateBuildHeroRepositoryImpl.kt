package com.example.honkaihelper.create_build_hero.data

import com.example.honkaihelper.create_build_hero.data.model.BuildHeroFromUser
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateBuildHeroRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
    private val createBuildHeroService: CreateBuildHeroService
): CreateBuildHeroRepository {
    override suspend fun getHero(idHero: Int): Hero = withContext(ioDispatcher) {
        return@withContext heroDao.getHero(idHero).toHero()
    }

    override suspend fun saveBuild(buildHeroFromUser: BuildHeroFromUser): NetworkResult<Boolean> {
        return handleApi { createBuildHeroService.saveBuild(buildHeroFromUser) }
    }
}