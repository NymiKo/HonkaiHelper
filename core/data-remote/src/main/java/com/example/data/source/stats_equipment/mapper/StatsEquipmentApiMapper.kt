package com.example.data.source.stats_equipment.mapper

import com.example.data.remote.api.stats_equipment.BuildStatsEquipmentDto
import com.example.domain.repository.build_stats_equipment.BuildStatsEquipmentModel

fun BuildStatsEquipmentDto.toBuildStatsEquipmentModel() = BuildStatsEquipmentModel(
    idBuildStatsEquipment, body, legs, sphere, rope, idHero
)