package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.db.contract.RoomContract
import com.example.data.db.entity.WeaponEntity
import com.example.data.db.models.weapon.WeaponWithHeroesRelation
import com.example.data.db.models.weapon.WeaponWithPathRelation

@Dao
interface WeaponDao {
    @Query("SELECT * FROM ${RoomContract.tableWeapons}")
    suspend fun getWeapons(): List<WeaponEntity>

    @Transaction
    @Query("SELECT * FROM ${RoomContract.tableWeapons} WHERE idWeapon = :idWeapon")
    suspend fun getWeaponWithPath(idWeapon: Int): WeaponWithPathRelation

    @Query("SELECT * FROM ${RoomContract.tableWeapons} WHERE idWeapon = :idWeapon")
    suspend fun getWeapon(idWeapon: Int): WeaponEntity

    @Transaction
    @Query("SELECT * FROM ${RoomContract.tableWeapons} WHERE idWeapon = :idWeapon")
    suspend fun getWeaponWithHeroes(idWeapon: Int): WeaponWithHeroesRelation

    @Query("SELECT * FROM ${RoomContract.tableWeapons} WHERE path = :path")
    suspend fun getWeaponByPath(path: Int): List<WeaponEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeapons(weapons: List<WeaponEntity>)
}