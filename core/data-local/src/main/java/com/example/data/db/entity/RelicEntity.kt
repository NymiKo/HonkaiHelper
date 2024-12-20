package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.common.RelicModel
import com.example.data.db.contract.RoomContract

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
        fun toRelicEntity(relic: RelicModel) = RelicEntity(
            idRelic = relic.idRelic,
            title = relic.title,
            descriptionTwoParts = relic.descriptionTwoParts,
            descriptionFourParts = relic.descriptionFourParts
        )
    }

    fun toRelic() = RelicModel(
        idRelic = idRelic,
        title = title,
        descriptionTwoParts = descriptionTwoParts,
        descriptionFourParts = descriptionFourParts,
        image = image
    )
}