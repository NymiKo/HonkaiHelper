package com.example.honkaihelper.base_build_hero.data

import com.example.honkaihelper.base_build_hero.data.model.FullHeroInfo
import com.example.honkaihelper.data.local.dao.HeroDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BaseBuildHeroRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao
) : BaseBuildHeroRepository {
    override suspend fun getHero(idHero: Int): FullHeroInfo {
        return withContext(ioDispatcher) {
            val result = heroDao.getHeroWithPathAndElement(idHero)
            return@withContext FullHeroInfo(
                result.heroEntity.toHero(),
                result.pathEntity.toPath(),
                result.elementEntity.toElement(),
                result.abilityEntity.map { it.toAbility() },
                result.eidolonEntity.map { it.toEidolon() })
        }
    }
}