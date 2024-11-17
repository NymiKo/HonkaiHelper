package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.local.contract.RoomContract
import com.example.domain.repository.decoration.DecorationModel

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
