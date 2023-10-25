package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.honkaihelper.base_build_hero.data.model.Element
import com.example.honkaihelper.base_build_hero.data.model.Path
import com.example.honkaihelper.data.local.contract.RoomContract

@Entity(
    tableName = RoomContract.tableElements
)
data class ElementEntity(
    @PrimaryKey(autoGenerate = false)
    val idElement: Int,
    val title: String,
    val image: String
) {
    companion object {
        fun toElementEntity(element: Element) = ElementEntity(
            idElement = element.idElement,
            title = element.title,
            image = element.image
        )
    }

    fun toElement() = Element(
        idElement = idElement,
        title = title,
        image = image
    )
}
