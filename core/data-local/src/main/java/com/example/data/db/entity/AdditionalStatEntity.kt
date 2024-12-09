package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.contract.RoomContract

@Entity(tableName = RoomContract.tableAdditionalStats)
data class AdditionalStatEntity(
    @PrimaryKey(autoGenerate = false)
    val idAdditionalStat: Int,
    val idStat: Int,
    val idHero: Int,
)
