package com.example.tanorami.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tanorami.core.database.contract.RoomContract
import com.example.tanorami.info_about_hero.data.model.Eidolon

@Entity(tableName = RoomContract.tableEidolons)
data class EidolonEntity(
    @PrimaryKey(autoGenerate = false)
    val idEidolon: Int,
    val title: String,
    val description: String,
    val image: String = "",
    val idHero: Int
) {
    companion object {
        fun toEidolonEntity(eidolon: Eidolon) = EidolonEntity(
            idEidolon = eidolon.idEidolon,
            title = eidolon.title,
            description = eidolon.description,
            idHero = eidolon.idHero
        )
    }

    fun toEidolon() = Eidolon(
        idEidolon = idEidolon,
        title = title,
        description = description,
        image = image,
        idHero = idHero
    )
}