package com.example.tanorami.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tanorami.core.database.contract.RoomContract
import com.example.tanorami.core.database.entity.BuildRelicEntity

@Dao
interface BuildRelicDao {
    @Query("SELECT * FROM ${RoomContract.tableBuildRelic}")
    suspend fun getBuildRelic(): List<BuildRelicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildRelic(buildRelic: List<BuildRelicEntity>)
}