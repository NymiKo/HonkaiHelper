package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.database.contract.RoomContract
import com.example.core.database.entity.ElementEntity

@Dao
interface ElementDao {
    @Query("SELECT * FROM ${RoomContract.tableElements}")
    suspend fun getElements(): List<ElementEntity>

    @Insert
    suspend fun insertElements(elements: List<ElementEntity>)
}