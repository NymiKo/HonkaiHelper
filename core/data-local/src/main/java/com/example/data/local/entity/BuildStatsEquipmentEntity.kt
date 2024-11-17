package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.local.contract.RoomContract
import com.example.domain.repository.build_stats_equipment.BuildStatsEquipmentModel

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
        fun toBuildStatsEquipmentEntity(buildStatsEquipment: BuildStatsEquipmentModel) =
            BuildStatsEquipmentEntity(
                idBuildStatsEquipment = buildStatsEquipment.idBuildStatsEquipment,
                body = buildStatsEquipment.body,
                legs = buildStatsEquipment.legs,
                sphere = buildStatsEquipment.sphere,
                rope = buildStatsEquipment.rope,
                idHero = buildStatsEquipment.idHero
            )
    }

    fun toBuildStatsEquipment() = BuildStatsEquipmentModel(
        idBuildStatsEquipment = idBuildStatsEquipment,
        body = body,
        legs = legs,
        sphere = sphere,
        rope = rope,
        idHero = idHero
    )
}