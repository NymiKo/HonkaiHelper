package com.example.honkaihelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.honkaihelper.base_build_hero.data.model.BuildDecoration
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.data.local.entity.BuildDecorationEntity

@Dao
interface BuildDecorationDao {
    @Query("SELECT * FROM ${RoomContract.tableBuildDecoration}")
    suspend fun getBuildDecorations(): List<BuildDecorationEntity>

    @Insert
    suspend fun insertBuildDecorations(buildDecorations: List<BuildDecoration>)
}