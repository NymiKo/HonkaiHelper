package com.example.honkaihelper.create_build_hero.data

import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateBuildHeroRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao
): CreateBuildHeroRepository {
    override suspend fun getHero(idHero: Int): HeroWithNameAvatarRarity = withContext(ioDispatcher) {
        return@withContext heroDao.getHeroWithNameAvatarRarity(idHero)
    }
}