package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.local.contract.RoomContract
import com.example.data.local.entity.ElementEntity

@Dao
interface ElementDao {
    @Query("SELECT * FROM ${RoomContract.tableElements}")
    suspend fun getElements(): List<ElementEntity>

    @Insert
    suspend fun insertElements(elements: List<ElementEntity>)
}