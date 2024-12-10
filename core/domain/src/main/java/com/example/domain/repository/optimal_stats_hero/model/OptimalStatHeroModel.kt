package com.example.domain.repository.optimal_stats_hero.model

data class OptimalStatHeroModel(
    val idOptimalStat: Int,
    val idStat: Int,
    val statValue: String,
    val remark: String?,
    val idHero: Int,
)
