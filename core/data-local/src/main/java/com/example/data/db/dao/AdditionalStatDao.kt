package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.contract.RoomContract
import com.example.data.db.entity.AdditionalStatEntity

@Dao
interface AdditionalStatDao {
    @Query("SELECT * FROM ${RoomContract.tableAdditionalStats}")
    suspend fun getAdditionalStats(): List<AdditionalStatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdditionalStats(additionalStats: List<AdditionalStatEntity>)
}