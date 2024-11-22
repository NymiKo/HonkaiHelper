package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.contract.RoomContract

@Entity(
    tableName = RoomContract.tableHeroes
)
data class HeroEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val story: String,
    val rarity: Boolean,
    val idPath: Int,
    val idElement: Int,
    val localAvatarPath: String = "",
    val localSplashArtPath: String = "",
)
