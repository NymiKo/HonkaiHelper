package com.example.tanorami.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tanorami.data.local.contract.RoomContract
import com.example.tanorami.data.local.entity.PathEntity

@Dao
interface PathDao {

    @Query("SELECT * FROM ${RoomContract.tablePaths}")
    suspend fun getPaths(): List<PathEntity>

    @Insert
    suspend fun insertPaths(paths: List<PathEntity>)
}