package com.example.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.database.contract.RoomContract
import com.example.core.domain.repository.element.Element

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
        fun toElementEntity(element: Element) = ElementEntity(
            idElement = element.idElement,
            title = element.title
        )
    }

    fun toElement() = Element(
        idElement = idElement,
        title = title,
        image = image
    )
}
