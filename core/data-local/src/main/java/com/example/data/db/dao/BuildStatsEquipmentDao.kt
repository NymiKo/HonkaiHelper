package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.contract.RoomContract
import com.example.data.db.entity.BuildStatsEquipmentEntity

@Dao
interface BuildStatsEquipmentDao {
    @Query("SELECT * FROM ${RoomContract.tableBuildStatsEquipment}")
    suspend fun getBuildStatsEquipment(): List<BuildStatsEquipmentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildStatsEquipment(buildStatsEquipment: List<BuildStatsEquipmentEntity>)
}