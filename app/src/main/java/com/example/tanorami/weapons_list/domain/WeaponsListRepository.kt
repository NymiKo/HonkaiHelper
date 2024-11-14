package com.example.tanorami.weapons_list.domain

import com.example.core.domain.repository.weapon.Weapon

interface WeaponsListRepository {
    suspend fun getWeaponsList(): List<Weapon>
}