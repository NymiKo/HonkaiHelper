package com.example.tanorami.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tanorami.data.local.contract.RoomContract
import com.example.tanorami.data.local.entity.BuildRelicEntity

@Dao
interface BuildRelicDao {
    @Query("SELECT * FROM ${RoomContract.tableBuildRelic}")
    suspend fun getBuildRelic(): List<BuildRelicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildRelic(buildRelic: List<BuildRelicEntity>)
}