package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.honkaihelper.base_build_hero.data.model.BuildStatsEquipment
import com.example.honkaihelper.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableBuildStatsEquipment)
data class BuildStatsEquipmentEntity(
    @PrimaryKey(autoGenerate = false)
    val idBuildStatsEquipment: Int,
    val body: String,
    val legs: String,
    val sphere: String,
    val rope: String,
    val idHero: Int
) {
    companion object {
        fun toBuildStatsEquipmentEntity(buildStatsEquipment: BuildStatsEquipment) =
            BuildStatsEquipmentEntity(
                idBuildStatsEquipment = buildStatsEquipment.idBuildStatsEquipment,
                body = buildStatsEquipment.body,
                legs = buildStatsEquipment.legs,
                sphere = buildStatsEquipment.sphere,
                rope = buildStatsEquipment.rope,
                idHero = buildStatsEquipment.idHero
            )
    }

    fun toBuildStatsEquipment() = BuildStatsEquipment(
        idBuildStatsEquipment = idBuildStatsEquipment,
        body = body,
        legs = legs,
        sphere = sphere,
        rope = rope,
        idHero = idHero
    )
}