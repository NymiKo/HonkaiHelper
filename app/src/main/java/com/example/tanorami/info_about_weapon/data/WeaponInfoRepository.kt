package com.example.tanorami.info_about_weapon.data

import com.example.tanorami.info_about_weapon.data.model.FullInfoAboutWeapon

interface WeaponInfoRepository {
    suspend fun getWeapon(idWeapon: Int): FullInfoAboutWeapon
}