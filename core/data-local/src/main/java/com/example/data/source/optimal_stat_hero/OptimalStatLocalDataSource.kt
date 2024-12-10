package com.example.data.source.optimal_stat_hero

import com.example.data.db.entity.OptimalStatHeroEntity

interface OptimalStatLocalDataSource {
    suspend fun getOptimalStats(): List<OptimalStatHeroEntity>
    suspend fun insertOptimalStatsHero(optimalStatsHero: List<OptimalStatHeroEntity>)
}