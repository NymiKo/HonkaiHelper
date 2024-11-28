package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.contract.RoomContract
import com.example.domain.repository.weapon.models.WeaponModel

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
        fun toWeaponEntity(weapon: WeaponModel) = WeaponEntity(
            idWeapon = weapon.idWeapon,
            name = weapon.name,
            story = weapon.story,
            description = weapon.description,
            path = weapon.path,
            rarity = weapon.rarity
        )
    }

    fun toWeapon() = WeaponModel(
        idWeapon = idWeapon,
        name = name,
        story = story,
        description = description,
        image = image,
        path = path,
        rarity = rarity
    )
}