package com.example.tanorami.info_about_hero.data

import com.example.tanorami.core.database.dao.HeroDao
import com.example.tanorami.core.di.IODispatcher
import com.example.tanorami.info_about_hero.data.model.FullHeroInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoAboutHeroRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao
) : InfoAboutHeroRepository {
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