package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.local.contract.RoomContract
import com.example.domain.repository.eidolon.EidolonModel

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
        fun toEidolonEntity(eidolon: EidolonModel) = EidolonEntity(
            idEidolon = eidolon.idEidolon,
            title = eidolon.title,
            description = eidolon.description,
            idHero = eidolon.idHero
        )
    }

    fun toEidolon() = EidolonModel(
        idEidolon = idEidolon,
        title = title,
        description = description,
        image = image,
        idHero = idHero
    )
}