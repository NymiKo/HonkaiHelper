package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.contract.RoomContract
import com.example.domain.repository.element.ElementModel

@Entity(
    tableName = RoomContract.tableElements
)
data class ElementEntity(
    @PrimaryKey(autoGenerate = false)
    val idElement: Int,
    val title: String,
    val image: String = ""
) {
    companion object {
        fun toElementEntity(element: ElementModel) = ElementEntity(
            idElement = element.idElement,
            title = element.title
        )
    }

    fun toElement() = ElementModel(
        idElement = idElement,
        title = title,
        image = image
    )
}
