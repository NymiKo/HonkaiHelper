package com.example.weapons_list.domain

import com.example.common.WeaponModel

internal interface WeaponsListRepository {
    suspend fun getWeaponsList(): List<WeaponModel>
}