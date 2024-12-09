package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.contract.RoomContract

@Entity(tableName = RoomContract.tableStats)
data class StatEntity(
    @PrimaryKey(autoGenerate = false)
    val idStat: Int,
    val statName: String,
)
