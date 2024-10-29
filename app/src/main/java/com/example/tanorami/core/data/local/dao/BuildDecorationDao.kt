package com.example.tanorami.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tanorami.core.data.local.contract.RoomContract
import com.example.tanorami.core.data.local.entity.BuildDecorationEntity

@Dao
interface BuildDecorationDao {
    @Query("SELECT * FROM ${RoomContract.tableBuildDecoration}")
    suspend fun getBuildDecorations(): List<BuildDecorationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildDecorations(buildDecorations: List<BuildDecorationEntity>)
}