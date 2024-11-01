package com.example.tanorami.weapons_list.data

import com.example.tanorami.weapons_list.data.models.Weapon

interface WeaponsListRepository {
    suspend fun getWeaponsList(): List<Weapon>
}