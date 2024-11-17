package com.example.tanorami.weapons_list.domain

interface WeaponsListRepository {
    suspend fun getWeaponsList(): List<com.example.domain.repository.weapon.Weapon>
}