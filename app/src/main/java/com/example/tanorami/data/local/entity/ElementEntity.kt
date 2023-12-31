package com.example.tanorami.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tanorami.info_about_hero.data.model.Element
import com.example.tanorami.data.local.contract.RoomContract

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
