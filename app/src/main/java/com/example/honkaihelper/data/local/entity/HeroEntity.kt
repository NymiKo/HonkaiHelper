package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.honkaihelper.data.local.contract.RoomContract

@Entity(
    tableName = RoomContract.tableHeroes
)
data class HeroEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val avatar: String,
    val rarity: Boolean,
    val idPath: Int
)
