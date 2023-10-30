package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import com.example.honkaihelper.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableOptimalStatsHero)
data class OptimalStatsHeroEntity(
    val idOptimalStats: Int,
    val attack: String,
    val speed: String,
    val energy: String,
    val hp: String,
    val defence: String,
    val critRate: String,
    val critDamage: String,
    val idHero: Int
)
