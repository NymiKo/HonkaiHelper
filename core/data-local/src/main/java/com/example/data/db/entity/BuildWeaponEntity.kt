package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.contract.RoomContract
import com.example.domain.repository.weapon.models.BuildWeaponModel

@Entity(tableName = RoomContract.tableBuildWeapon)
data class BuildWeaponEntity(
    @PrimaryKey(autoGenerate = false)
    val idBuildWeapon: Int,
    val idWeapon: Int,
    val top: Int,
    val tier: Int,
    val idHero: Int,
) {
    companion object {
        fun toBuildWeaponEntity(buildWeapon: BuildWeaponModel) = BuildWeaponEntity(
            idBuildWeapon = buildWeapon.idBuildWeapon,
            idWeapon = buildWeapon.idWeapon,
            top = buildWeapon.top,
            tier = buildWeapon.tier,
            idHero = buildWeapon.idHero
        )
    }

    fun toBuildWeapon() = BuildWeaponModel(
        idBuildWeapon = idBuildWeapon,
        idWeapon = idWeapon,
        top = top,
        tier = tier,
        idHero = idHero
    )
}