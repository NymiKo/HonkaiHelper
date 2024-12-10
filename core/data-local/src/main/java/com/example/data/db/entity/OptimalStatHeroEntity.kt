package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.contract.RoomContract

@Entity(tableName = RoomContract.tableOptimalStatsHero)
data class OptimalStatHeroEntity(
    @PrimaryKey(autoGenerate = false)
    val idOptimalStat: Int,
    val idStat: Int,
    val statValue: String,
    val remark: String?,
    val idHero: Int,
)
