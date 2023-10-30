package com.example.honkaihelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.data.local.entity.OptimalStatsHeroEntity

@Dao
interface OptimalStatsHeroDao {
    @Query("SELECT * FROM ${RoomContract.tableOptimalStatsHero}")
    suspend fun getOptimalStats(): List<OptimalStatsHeroEntity>

    @Insert
    suspend fun insertOptimalStatsHero(optimalStatsHero: List<OptimalStatsHeroEntity>)
}