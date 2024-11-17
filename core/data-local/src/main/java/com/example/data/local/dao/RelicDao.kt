package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.contract.RoomContract
import com.example.data.local.entity.RelicEntity

@Dao
interface RelicDao {
    @Query("SELECT * FROM ${RoomContract.tableRelics}")
    suspend fun getRelics(): List<RelicEntity>

    @Query("SELECT * FROM ${RoomContract.tableRelics} WHERE idRelic = :idRelic")
    suspend fun getRelic(idRelic: Int): RelicEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRelics(relics: List<RelicEntity>)
}