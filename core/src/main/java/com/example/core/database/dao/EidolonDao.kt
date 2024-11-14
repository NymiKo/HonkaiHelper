package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.database.contract.RoomContract
import com.example.core.database.entity.EidolonEntity

@Dao
interface EidolonDao {
    @Query("SELECT * FROM ${RoomContract.tableEidolons}")
    suspend fun getEidolons(): List<EidolonEntity>

    @Insert
    suspend fun insertEidolons(eidolons: List<EidolonEntity>)
}