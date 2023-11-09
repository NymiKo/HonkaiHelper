package com.example.honkaihelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.data.local.entity.WeaponEntity
import com.example.honkaihelper.data.local.models.WeaponWithPath

@Dao
interface WeaponDao {
    @Query("SELECT * FROM ${RoomContract.tableWeapons}")
    suspend fun getWeapons(): List<WeaponEntity>

    @Transaction
    @Query("SELECT * FROM ${RoomContract.tableWeapons} WHERE idWeapon = :idWeapon")
    suspend fun getWeapon(idWeapon: Int): WeaponWithPath

    @Insert
    suspend fun insertWeapons(weapons: List<WeaponEntity>)
}