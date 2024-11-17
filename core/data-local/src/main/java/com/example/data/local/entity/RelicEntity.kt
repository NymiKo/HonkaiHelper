package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.local.contract.RoomContract
import com.example.domain.repository.relic.RelicModel

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