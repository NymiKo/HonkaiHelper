package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.contract.RoomContract
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
    fun toBuildStatsEquipment() = BuildStatsEquipmentModel(
        idBuildStatsEquipment = idBuildStatsEquipment,
        body = body,
        legs = legs,
        sphere = sphere,
        rope = rope,
        idHero = idHero
    )
}