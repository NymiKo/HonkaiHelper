package com.example.tanorami.weapon.data

import com.example.tanorami.weapon.data.model.FullWeaponInfo

interface WeaponInfoRepository {
    suspend fun getWeapon(idWeapon: Int): FullWeaponInfo
}