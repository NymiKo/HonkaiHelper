package com.example.honkaihelper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.data.local.entity.BuildWeaponEntity

@Dao
interface BuildWeaponDao {
    @Query("SELECT * FROM ${RoomContract.tableBuildWeapon}")
    suspend fun getBuildWeapon(): List< BuildWeaponEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildWeapon(buildWeapons: List<BuildWeaponEntity>)
}