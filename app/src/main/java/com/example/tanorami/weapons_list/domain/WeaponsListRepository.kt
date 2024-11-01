package com.example.tanorami.weapons_list.domain

import com.example.tanorami.weapons_list.domain.models.Weapon

interface WeaponsListRepository {
    suspend fun getWeaponsList(): List<Weapon>
}