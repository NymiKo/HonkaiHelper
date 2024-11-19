package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.contract.RoomContract
import com.example.data.db.entity.BuildRelicEntity

@Dao
interface BuildRelicDao {
    @Query("SELECT * FROM ${RoomContract.tableBuildRelic}")
    suspend fun getBuildRelic(): List<BuildRelicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildRelic(buildRelic: List<BuildRelicEntity>)
}