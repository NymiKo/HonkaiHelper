package com.example.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.database.contract.RoomContract
import com.example.core.domain.repository.hero.model.HeroModel

@Entity(
    tableName = RoomContract.tableHeroes
)
data class HeroEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val story: String,
    val rarity: Boolean,
    val idPath: Int,
    val idElement: Int,
    val localAvatarPath: String = "",
    val localSplashArtPath: String =""
) {
    companion object {
        fun toHeroEntity(heroModel: HeroModel) = HeroEntity(
            id = heroModel.id,
            name = heroModel.name,
            story = heroModel.story,
            rarity = heroModel.rarity,
            idPath = heroModel.idPath,
            idElement = heroModel.idElement
        )
    }

    fun toHeroModel() = HeroModel(
        id = id,
        name = name,
        story = story,
        avatar = localAvatarPath,
        splashArt = localSplashArtPath,
        rarity = rarity,
        idPath = idPath,
        idElement = idElement
    )
}
