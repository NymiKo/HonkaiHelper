package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.database.contract.RoomContract
import com.example.core.database.entity.OptimalStatsHeroEntity

@Dao
interface OptimalStatsHeroDao {
    @Query("SELECT * FROM ${RoomContract.tableOptimalStatsHero}")
    suspend fun getOptimalStats(): List<OptimalStatsHeroEntity>

    @Insert
    suspend fun insertOptimalStatsHero(optimalStatsHero: List<OptimalStatsHeroEntity>)
}