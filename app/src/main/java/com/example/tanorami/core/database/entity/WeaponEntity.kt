package com.example.tanorami.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tanorami.core.database.contract.RoomContract
import com.example.tanorami.weapons_list.domain.models.Weapon

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