package com.example.data.source.optimal_stat_hero.mapper

import com.example.data.remote.api.optimal_stat.model.OptimalStatHeroDto
import com.example.domain.repository.optimal_stats_hero.model.OptimalStatHeroModel

fun OptimalStatHeroDto.toOptimalStatHeroModel() = OptimalStatHeroModel(
    idOptimalStat, idStat, statValue, remark, idHero
)