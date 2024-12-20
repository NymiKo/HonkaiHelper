package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.db.contract.RoomContract
import com.example.data.db.entity.OptimalStatsHeroEntity

@Dao
interface OptimalStatsHeroDao {
    @Query("SELECT * FROM ${RoomContract.tableOptimalStatsHero}")
    suspend fun getOptimalStats(): List<OptimalStatsHeroEntity>

    @Insert
    suspend fun insertOptimalStatsHero(optimalStatsHero: List<OptimalStatsHeroEntity>)
}