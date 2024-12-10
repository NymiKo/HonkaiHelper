package com.example.data.remote.api.optimal_stat.model

data class OptimalStatHeroDto(
    val idOptimalStat: Int,
    val idStat: Int,
    val statValue: String,
    val remark: String?,
    val idHero: Int,
)
