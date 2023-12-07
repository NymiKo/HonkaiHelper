package com.example.tanorami.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tanorami.base_build_hero.data.model.BuildDecoration
import com.example.tanorami.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableBuildDecoration)
data class BuildDecorationEntity(
    @PrimaryKey(autoGenerate = false)
    val idBuildDecoration: Int,
    val idDecoration: Int,
    val top: Int,
    val idHero: Int
) {
    companion object {
        fun toBuildDecorationEntity(buildDecoration: BuildDecoration) = BuildDecorationEntity(
            idBuildDecoration = buildDecoration.idBuildDecoration,
            idDecoration = buildDecoration.idDecoration,
            top = buildDecoration.top,
            idHero = buildDecoration.idHero
        )
    }

    fun toBuildDecoration() = BuildDecoration(
        idBuildDecoration = idBuildDecoration,
        idDecoration = idDecoration,
        top = top,
        idHero = idHero
    )
}
