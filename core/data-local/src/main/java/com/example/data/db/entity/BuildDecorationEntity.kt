package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.contract.RoomContract
import com.example.domain.repository.decoration.BuildDecorationModel

@Entity(tableName = RoomContract.tableBuildDecoration)
data class BuildDecorationEntity(
    @PrimaryKey(autoGenerate = false)
    val idBuildDecoration: Int,
    val idDecoration: Int,
    val top: Int,
    val idHero: Int
) {
    companion object {
        fun toBuildDecorationEntity(buildDecoration: BuildDecorationModel) = BuildDecorationEntity(
            idBuildDecoration = buildDecoration.idBuildDecoration,
            idDecoration = buildDecoration.idDecoration,
            top = buildDecoration.top,
            idHero = buildDecoration.idHero
        )
    }

    fun toBuildDecoration() = BuildDecorationModel(
        idBuildDecoration = idBuildDecoration,
        idDecoration = idDecoration,
        top = top,
        idHero = idHero
    )
}
