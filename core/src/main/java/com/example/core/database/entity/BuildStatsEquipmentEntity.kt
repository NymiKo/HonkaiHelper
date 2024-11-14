package com.example.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.database.contract.RoomContract
import com.example.core.domain.repository.stats_equipment.BuildStatsEquipment

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
                idBuildStatsEquipment = buildStatsEquipment.idStats,
                body = buildStatsEquipment.body,
                legs = buildStatsEquipment.legs,
                sphere = buildStatsEquipment.sphere,
                rope = buildStatsEquipment.rope,
                idHero = buildStatsEquipment.idHero
            )
    }

    fun toBuildStatsEquipment() = BuildStatsEquipment(
        idStats = idBuildStatsEquipment,
        body = body,
        legs = legs,
        sphere = sphere,
        rope = rope,
        idHero = idHero
    )
}