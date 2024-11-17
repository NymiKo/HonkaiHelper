package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.local.contract.RoomContract
import com.example.domain.repository.weapon.BuildWeaponModel

@Entity(tableName = RoomContract.tableBuildWeapon)
data class BuildWeaponEntity(
    @PrimaryKey(autoGenerate = false)
    val idBuildWeapon: Int,
    val idWeapon: Int,
    val top: Int,
    val idHero: Int,
) {
    companion object {
        fun toBuildWeaponEntity(buildWeapon: BuildWeaponModel) = BuildWeaponEntity(
            idBuildWeapon = buildWeapon.idBuildWeapon,
            idWeapon = buildWeapon.idWeapon,
            top = buildWeapon.top,
            idHero = buildWeapon.idHero
        )
    }

    fun toBuildWeapon() = BuildWeaponModel(
        idBuildWeapon = idBuildWeapon,
        idWeapon = idWeapon,
        top = top,
        idHero = idHero
    )
}