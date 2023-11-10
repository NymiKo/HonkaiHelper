package com.example.honkaihelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.data.local.entity.DecorationEntity

@Dao
interface DecorationDao {
    @Query("SELECT * FROM ${RoomContract.tableDecorations}")
    suspend fun getDecorations(): List<DecorationEntity>

    @Query("SELECT * FROM ${RoomContract.tableDecorations} WHERE idDecoration = :idDecoration")
    suspend fun getDecoration(idDecoration: Int): DecorationEntity

    @Insert
    suspend fun insertDecorations(decorations: List<DecorationEntity>)
}