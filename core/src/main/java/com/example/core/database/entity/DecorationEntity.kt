package com.example.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.database.contract.RoomContract
import com.example.core.domain.repository.decoration.Decoration

@Entity(tableName = RoomContract.tableDecorations)
data class DecorationEntity(
    @PrimaryKey(autoGenerate = false)
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
