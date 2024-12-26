package com.example.tanorami.base_build_hero.data

import com.example.data.db.dao.HeroDao
import com.example.data.source.optimal_stat_hero.mapper.toOptimalStatHeroForBuildModel
import com.example.domain.di.DispatcherIo
import com.example.tanorami.base_build_hero.data.model.FullBaseBuildHero
import com.example.tanorami.base_build_hero.data.model.WeaponForBuildModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BaseBuildHeroRepositoryImpl @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
) : BaseBuildHeroRepository {
    override suspend fun getBaseBuildHero(idHero: Int): FullBaseBuildHero =
        withContext(ioDispatcher) {
            val result = heroDao.getFullBaseBuildHero(idHero)
            return@withContext FullBaseBuildHero(
                result.id,
                result.weaponsForBuild.sortedBy { it.top }.map {
                    WeaponForBuildModel(
                        weapon = it.weaponEntity.toWeapon(),
                        tier = it.tier
                    )
                },
                result.relicsForBuild.sortedBy { it.top }.map { it.relic.toRelic() },
                result.decorationsForBuild.sortedBy { it.top }.map { it.decoration.toDecoration() },
                listOf(
                    result.buildStatsEquipmentEntity.body,
                    result.buildStatsEquipmentEntity.legs,
                    result.buildStatsEquipmentEntity.sphere,
                    result.buildStatsEquipmentEntity.rope
                ),
                result.buildAdditionalStatsEntity.map { it.statEntity.statName },
                result.buildOptimalStatsHeroEntity.map { it.toOptimalStatHeroForBuildModel() }
            )
        }
}