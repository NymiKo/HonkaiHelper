package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.local.contract.RoomContract
import com.example.domain.repository.relic.BuildRelicModel

@Entity(tableName = RoomContract.tableBuildRelic)
data class BuildRelicEntity(
    @PrimaryKey(autoGenerate = false)
    val idBuildRelic: Int,
    val idRelic: Int,
    val top: Int,
    val idHero: Int
) {
    companion object {
        fun toBuildRelicEntity(buildRelic: BuildRelicModel) = BuildRelicEntity(
            idBuildRelic = buildRelic.idBuildRelic,
            idRelic = buildRelic.idRelic,
            top = buildRelic.top,
            idHero = buildRelic.idHero
        )
    }

    fun toBuildRelic() = BuildRelicModel(
        idBuildRelic = idBuildRelic,
        idRelic = idRelic,
        top = top,
        idHero = idHero
    )
}
