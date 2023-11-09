package com.example.honkaihelper.weapon.data

import com.example.honkaihelper.base_build_hero.data.model.Weapon

interface WeaponInfoRepository {
    suspend fun getWeapon(idWeapon: Int): Weapon
}