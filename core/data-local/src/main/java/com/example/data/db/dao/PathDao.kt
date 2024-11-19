package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.db.contract.RoomContract
import com.example.data.db.entity.PathEntity

@Dao
interface PathDao {

    @Query("SELECT * FROM ${RoomContract.tablePaths}")
    suspend fun getPaths(): List<PathEntity>

    @Insert
    suspend fun insertPaths(paths: List<PathEntity>)
}