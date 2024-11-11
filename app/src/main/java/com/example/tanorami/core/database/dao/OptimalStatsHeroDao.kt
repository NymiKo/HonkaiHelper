package com.example.tanorami.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tanorami.core.database.contract.RoomContract
import com.example.tanorami.core.database.entity.OptimalStatsHeroEntity

@Dao
interface OptimalStatsHeroDao {
    @Query("SELECT * FROM ${RoomContract.tableOptimalStatsHero}")
    suspend fun getOptimalStats(): List<OptimalStatsHeroEntity>

    @Insert
    suspend fun insertOptimalStatsHero(optimalStatsHero: List<OptimalStatsHeroEntity>)
}