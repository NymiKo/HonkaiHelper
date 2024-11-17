package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.contract.RoomContract
import com.example.data.local.entity.BuildStatsEquipmentEntity

@Dao
interface BuildStatsEquipmentDao {
    @Query("SELECT * FROM ${RoomContract.tableBuildStatsEquipment}")
    suspend fun getBuildStatsEquipment(): List<BuildStatsEquipmentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildStatsEquipment(buildStatsEquipment: List<BuildStatsEquipmentEntity>)
}