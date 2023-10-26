package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import com.example.honkaihelper.base_build_hero.data.model.Decoration
import com.example.honkaihelper.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableDecorations)
data class DecorationEntity(
    val idDecoration: Int,
    val title: String,
    val description: String,
    val image: String = ""
) {
    companion object {
        fun toDecorationEntity(decoration: Decoration) = DecorationEntity(
            idDecoration = decoration.idDecoration,
            title = decoration.title,
            description = decoration.description
        )
    }

    fun toDecoration() = Decoration(
        idDecoration = idDecoration,
        title = title,
        description = description,
        image = image
    )
}
