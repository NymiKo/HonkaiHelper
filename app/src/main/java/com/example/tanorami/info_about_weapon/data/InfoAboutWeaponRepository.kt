package com.example.tanorami.info_about_weapon.data

import com.example.domain.repository.weapon.models.WeaponWithHeroesModel

interface InfoAboutWeaponRepository {
    suspend fun getWeapon(idWeapon: Int): WeaponWithHeroesModel
}