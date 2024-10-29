package com.example.tanorami.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tanorami.core.data.local.contract.RoomContract
import com.example.tanorami.info_about_hero.data.model.Element

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
