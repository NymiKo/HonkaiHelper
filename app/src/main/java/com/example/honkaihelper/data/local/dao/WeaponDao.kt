package com.example.honkaihelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.data.local.entity.WeaponEntity

@Dao
interface WeaponDao {
    @Query("SELECT * FROM ${RoomContract.tableWeapons}")
    suspend fun getWeapons(): List<WeaponEntity>

    @Insert
    suspend fun insertWeapons(weapons: List<WeaponEntity>)
}