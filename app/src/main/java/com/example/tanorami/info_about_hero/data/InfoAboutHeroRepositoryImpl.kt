package com.example.tanorami.info_about_hero.data

import com.example.data.source.ability.toAbilityModel
import com.example.data.source.eidolon.toEidolonModel
import com.example.data.source.hero.HeroLocalDataSource
import com.example.domain.di.IODispatcher
import com.example.tanorami.info_about_hero.data.model.FullHeroInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoAboutHeroRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val heroLocalDataSource: HeroLocalDataSource
) : InfoAboutHeroRepository {
    override suspend fun getHero(idHero: Int): FullHeroInfo {
        return withContext(ioDispatcher) {
            val result = heroLocalDataSource.getHeroWithPathAndElement(idHero)
            return@withContext FullHeroInfo(
                result.heroEntity.toHeroModel(),
                result.pathEntity.toPath(),
                result.elementEntity.toElement(),
                result.abilityEntity.map { it.toAbilityModel() },
                result.eidolonEntity.map { it.toEidolonModel() })
        }
    }
}