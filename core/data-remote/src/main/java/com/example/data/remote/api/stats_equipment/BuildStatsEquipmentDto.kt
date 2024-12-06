package com.example.data.remote.api.stats_equipment

import com.google.gson.annotations.SerializedName

data class BuildStatsEquipmentDto(
    @SerializedName("idStats") val idBuildStatsEquipment: Int,
    val body: String,
    val legs: String,
    val sphere: String,
    val rope: String,
    val idHero: Int,
)
