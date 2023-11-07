package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.honkaihelper.base_build_hero.data.model.BuildWeapon
import com.example.honkaihelper.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableBuildWeapon)
data class BuildWeaponEntity(
    @PrimaryKey(autoGenerate = false)
    val idBuildWeapon: Int,
    val idWeapon: Int,
    val top: Int,
    val idHero: Int,
) {
    companion object {
        fun toBuildWeaponEntity(buildWeapon: BuildWeapon) = BuildWeaponEntity(
            idBuildWeapon = buildWeapon.idBuildWeapon,
            idWeapon = buildWeapon.idWeapon,
            top = buildWeapon.top,
            idHero = buildWeapon.idHero
        )
    }

    fun toBuildWeapon() = BuildWeapon(
        idBuildWeapon = idBuildWeapon,
        idWeapon = idWeapon,
        top = top,
        idHero = idHero
    )
}