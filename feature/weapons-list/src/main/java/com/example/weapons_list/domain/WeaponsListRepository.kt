package com.example.weapons_list.domain

import com.example.domain.repository.weapon.models.WeaponModel

internal interface WeaponsListRepository {
    suspend fun getWeaponsList(): List<WeaponModel>
}