package com.example.tanorami.weapons_list.domain

import com.example.domain.repository.weapon.WeaponModel

interface WeaponsListRepository {
    suspend fun getWeaponsList(): List<WeaponModel>
}