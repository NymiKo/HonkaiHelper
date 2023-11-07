package com.example.honkaihelper.base_build_hero.data

import com.example.honkaihelper.base_build_hero.data.model.FullBaseBuildHero
import com.example.honkaihelper.data.local.dao.HeroDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BaseBuildHeroRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao
) : BaseBuildHeroRepository {
    override suspend fun getBaseBuildHero(idHero: Int): FullBaseBuildHero =
        withContext(ioDispatcher) {
            val result = heroDao.getFullBaseBuildHero(idHero)
            return@withContext FullBaseBuildHero(
                id = result.id,
                optimalStatsHero = result.optimalStatsHeroEntity.toOptimalStatsHero(),
                weapons = result.weaponsEntity.sortedBy { it.top }.map { it.weapons.toWeapon() },
                relics = result.relicsEntity.sortedBy { it.top }.map { it.relic.toRelic() },
                decoration = result.decorationsEntity.sortedBy { it.top }
                    .map { it.decoration.toDecoration() },
                buildStatsEquipment = listOf(
                    result.buildStatsEquipmentEntity.body,
                    result.buildStatsEquipmentEntity.legs,
                    result.buildStatsEquipmentEntity.sphere,
                    result.buildStatsEquipmentEntity.rope
                )
            )
        }
}