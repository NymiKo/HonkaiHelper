package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.contract.RoomContract
import com.example.data.db.entity.StatEntity

@Dao
interface StatDao {
    @Query("SELECT * FROM ${RoomContract.tableStats}")
    suspend fun getStatsList(): List<StatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(statsList: List<StatEntity>)
}