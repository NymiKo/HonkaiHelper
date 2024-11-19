package com.example.data.source.build_stats_equipment

import com.example.data.db.entity.BuildStatsEquipmentEntity
import com.example.domain.repository.build_stats_equipment.BuildStatsEquipmentModel

fun BuildStatsEquipmentEntity.toBuildStatsEquipmentModel() = BuildStatsEquipmentModel(
    idBuildStatsEquipment,
    body,
    legs,
    sphere,
    rope,
    idHero,
)

fun BuildStatsEquipmentModel.toBuildStatsEquipmentEntity() = BuildStatsEquipmentEntity(
    idBuildStatsEquipment,
    body,
    legs,
    sphere,
    rope,
    idHero,
)