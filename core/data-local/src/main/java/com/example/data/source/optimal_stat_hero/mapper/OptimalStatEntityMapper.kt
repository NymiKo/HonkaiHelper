package com.example.data.source.optimal_stat_hero.mapper

import com.example.data.db.entity.OptimalStatHeroEntity
import com.example.data.db.models.stat.OptimalStatHeroForBuildRelation
import com.example.domain.repository.optimal_stats_hero.model.OptimalStatHeroForBuildModel
import com.example.domain.repository.optimal_stats_hero.model.OptimalStatHeroModel

fun OptimalStatHeroEntity.toOptimalStatHeroModel() = OptimalStatHeroModel(
    idOptimalStat, idStat, statValue, remark, idHero
)

fun OptimalStatHeroModel.toOptimalStatHeroEntity() = OptimalStatHeroEntity(
    idOptimalStat, idStat, statValue, remark, idHero
)

fun OptimalStatHeroForBuildRelation.toOptimalStatHeroForBuildModel() = OptimalStatHeroForBuildModel(
    idStat, statEntity.statName, statValue, remark
)