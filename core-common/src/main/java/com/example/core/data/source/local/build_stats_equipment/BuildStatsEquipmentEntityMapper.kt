package com.example.core.data.source.local.build_stats_equipment

import com.example.data.local.entity.BuildStatsEquipmentEntity
import com.example.domain.repository.build_stats_equipment.BuildStatsEquipmentModel

fun BuildStatsEquipmentEntity.toBuildStatsEquipmentModel() = BuildStatsEquipmentModel(
    idBuildStatsEquipment,
    body,
    legs,
    sphere,
    rope,
    idHero,
)