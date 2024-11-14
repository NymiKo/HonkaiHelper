package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.contract.RoomContract
import com.example.core.database.entity.BuildRelicEntity

@Dao
interface BuildRelicDao {
    @Query("SELECT * FROM ${RoomContract.tableBuildRelic}")
    suspend fun getBuildRelic(): List<BuildRelicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildRelic(buildRelic: List<BuildRelicEntity>)
}