package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.common.DecorationModel
import com.example.data.db.contract.RoomContract

@Entity(tableName = RoomContract.tableDecorations)
data class DecorationEntity(
    @PrimaryKey(autoGenerate = false)
    val idDecoration: Int,
    val title: String,
    val description: String,
    val image: String = ""
) {
    companion object {
        fun toDecorationEntity(decoration: DecorationModel) = DecorationEntity(
            idDecoration = decoration.idDecoration,
            title = decoration.title,
            description = decoration.description
        )
    }

    fun toDecoration() = DecorationModel(
        idDecoration = idDecoration,
        title = title,
        description = description,
        image = image
    )
}
