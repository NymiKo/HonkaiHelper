package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.contract.RoomContract
import com.example.data.db.entity.DecorationEntity

@Dao
interface DecorationDao {
    @Query("SELECT * FROM ${RoomContract.tableDecorations}")
    suspend fun getDecorations(): List<DecorationEntity>

    @Query("SELECT * FROM ${RoomContract.tableDecorations} WHERE idDecoration = :idDecoration")
    suspend fun getDecoration(idDecoration: Int): DecorationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDecorations(decorations: List<DecorationEntity>)
}