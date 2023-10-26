package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import com.example.honkaihelper.base_build_hero.data.model.Relic
import com.example.honkaihelper.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableRelics)
data class RelicEntity(
    val idRelic: Int,
    val title: String,
    val descriptionTwoParts: String,
    val descriptionFourParts: String,
    val image: String = ""
) {
    companion object {
        fun toRelicEntity(relic: Relic) = RelicEntity(
            idRelic = relic.idRelic,
            title = relic.title,
            descriptionTwoParts = relic.descriptionTwoParts,
            descriptionFourParts = relic.descriptionFourParts
        )
    }

    fun toRelic() = Relic(
        idRelic = idRelic,
        title = title,
        descriptionTwoParts = descriptionTwoParts,
        descriptionFourParts = descriptionFourParts,
        image = image
    )
}