package com.example.honkaihelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.data.local.entity.RelicEntity

@Dao
interface RelicDao {
    @Query("SELECT * FROM ${RoomContract.tableRelics}")
    suspend fun getRelics(): List<RelicEntity>

    @Insert
    suspend fun insertRelics(relics: List<RelicEntity>)
}