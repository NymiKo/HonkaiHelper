package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.contract.RoomContract
import com.example.data.db.entity.BuildWeaponEntity

@Dao
interface BuildWeaponDao {
    @Query("SELECT * FROM ${RoomContract.tableBuildWeapon}")
    suspend fun getBuildWeapon(): List<BuildWeaponEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuildWeapon(buildWeapons: List<BuildWeaponEntity>)
}