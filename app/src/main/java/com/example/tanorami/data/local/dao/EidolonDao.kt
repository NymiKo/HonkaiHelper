package com.example.tanorami.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tanorami.data.local.contract.RoomContract
import com.example.tanorami.data.local.entity.EidolonEntity

@Dao
interface EidolonDao {
    @Query("SELECT * FROM ${RoomContract.tableEidolons}")
    suspend fun getEidolons(): List<EidolonEntity>

    @Insert
    suspend fun insertEidolons(eidolons: List<EidolonEntity>)
}