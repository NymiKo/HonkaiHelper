package com.example.honkaihelper.weapon.data

import com.example.honkaihelper.weapon.data.model.FullWeaponInfo

interface WeaponInfoRepository {
    suspend fun getWeapon(idWeapon: Int): FullWeaponInfo
}