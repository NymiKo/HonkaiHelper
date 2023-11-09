package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.honkaihelper.base_build_hero.data.model.Weapon
import com.example.honkaihelper.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableWeapons)
data class WeaponEntity(
    @PrimaryKey(autoGenerate = false)
    val idWeapon: Int,
    val name: String,
    val story: String,
    val description: String,
    val image: String = "",
    val path: Int,
    val rarity: Int
) {
    companion object {
        fun toWeaponEntity(weapon: Weapon) = WeaponEntity(
            idWeapon = weapon.idWeapon,
            name = weapon.name,
            story = weapon.story,
            description = weapon.description,
            path = weapon.path,
            rarity = weapon.rarity
        )
    }

    fun toWeapon() = Weapon(
        idWeapon = idWeapon,
        name = name,
        story = story,
        description = description,
        image = image,
        path = path,
        rarity = rarity
    )
}