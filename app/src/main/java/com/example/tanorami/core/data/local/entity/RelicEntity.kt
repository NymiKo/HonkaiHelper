package com.example.tanorami.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tanorami.core.data.local.contract.RoomContract
import com.example.tanorami.info_about_hero.data.model.Relic

@Entity(tableName = RoomContract.tableRelics)
data class RelicEntity(
    @PrimaryKey(autoGenerate = false)
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