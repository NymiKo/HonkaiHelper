package com.example.data.source.optimal_stat_hero

import com.example.data.db.dao.OptimalStatsHeroDao
import com.example.data.db.entity.OptimalStatHeroEntity
import com.example.domain.di.DispatcherIo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OptimalStatLocalDataSourceImpl @Inject constructor(
    private val optimalStatsHeroDao: OptimalStatsHeroDao,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : OptimalStatLocalDataSource {
    override suspend fun getOptimalStats(): List<OptimalStatHeroEntity> {
        return withContext(ioDispatcher) {
            optimalStatsHeroDao.getOptimalStats()
        }
    }

    override suspend fun insertOptimalStatsHero(optimalStatsHero: List<OptimalStatHeroEntity>) {
        return withContext(ioDispatcher) {
            optimalStatsHeroDao.insertOptimalStatsHero(optimalStatsHero)
        }
    }
}