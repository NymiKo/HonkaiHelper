package com.example.tanorami.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.tanorami.core.data.local.contract.RoomContract
import com.example.tanorami.core.data.local.entity.WeaponEntity
import com.example.tanorami.core.data.local.models.weapon.WeaponWithPath

@Dao
interface WeaponDao {
    @Query("SELECT * FROM ${RoomContract.tableWeapons}")
    suspend fun getWeapons(): List<WeaponEntity>

    @Transaction
    @Query("SELECT * FROM ${RoomContract.tableWeapons} WHERE idWeapon = :idWeapon")
    suspend fun getWeaponWithPath(idWeapon: Int): WeaponWithPath

    @Query("SELECT * FROM ${RoomContract.tableWeapons} WHERE idWeapon = :idWeapon")
    suspend fun getWeapon(idWeapon: Int): WeaponEntity

    @Query("SELECT * FROM ${RoomContract.tableWeapons} WHERE path = :path")
    suspend fun getWeaponByPath(path: Int): List<WeaponEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeapons(weapons: List<WeaponEntity>)
}