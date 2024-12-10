package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.contract.RoomContract
import com.example.data.db.entity.OptimalStatHeroEntity

@Dao
interface OptimalStatsHeroDao {
    @Query("SELECT * FROM ${RoomContract.tableOptimalStatsHero}")
    suspend fun getOptimalStats(): List<OptimalStatHeroEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOptimalStatsHero(optimalStatsHero: List<OptimalStatHeroEntity>)
}