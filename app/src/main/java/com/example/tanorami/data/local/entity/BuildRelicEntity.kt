package com.example.tanorami.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tanorami.base_build_hero.data.model.BuildRelic
import com.example.tanorami.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableBuildRelic)
data class BuildRelicEntity(
    @PrimaryKey(autoGenerate = false)
    val idBuildRelic: Int,
    val idRelic: Int,
    val top: Int,
    val idHero: Int
) {
    companion object {
        fun toBuildRelicEntity(buildRelic: BuildRelic) = BuildRelicEntity(
            idBuildRelic = buildRelic.idBuildRelic,
            idRelic = buildRelic.idRelic,
            top = buildRelic.top,
            idHero = buildRelic.idHero
        )
    }

    fun toBuildRelic() = BuildRelic(
        idBuildRelic = idBuildRelic,
        idRelic = idRelic,
        top = top,
        idHero = idHero
    )
}
