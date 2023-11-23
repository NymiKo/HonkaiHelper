package com.example.honkaihelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.data.local.entity.BuildStatsEquipmentEntity

@Dao
interface BuildStatsEquipmentDao {
    @Query("SELECT * FROM ${RoomContract.tableBuildStatsEquipment}")
    suspend fun getBuildStatsEquipment(): List<BuildStatsEquipmentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildStatsEquipment(buildStatsEquipment: List<BuildStatsEquipmentEntity>)
}