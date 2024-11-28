package com.example.data.source.weapon

import com.example.data.db.entity.WeaponEntity
import com.example.data.db.models.weapon.WeaponWithPathRelation

interface WeaponLocalDataSource {
    suspend fun getWeapons(): List<WeaponEntity>
    suspend fun getWeaponWithPath(idWeapon: Int): WeaponWithPathRelation
    suspend fun getWeapon(idWeapon: Int): WeaponEntity
    suspend fun getWeaponByPath(path: Int): List<WeaponEntity>
    suspend fun insertWeapons(weapons: List<WeaponEntity>)
}