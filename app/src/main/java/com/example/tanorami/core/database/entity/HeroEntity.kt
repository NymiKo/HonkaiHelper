package com.example.tanorami.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tanorami.core.data.local.contract.RoomContract
import com.example.tanorami.heroes.data.model.Hero

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
        fun toHeroEntity(hero: Hero) = HeroEntity(
            id = hero.id,
            name = hero.name,
            story = hero.story,
            rarity = hero.rarity,
            idPath = hero.path,
            idElement = hero.element
        )
    }

    fun toHero() = Hero(
        id = id,
        name = name,
        story = story,
        avatar = localAvatarPath,
        splashArt = localSplashArtPath,
        rarity = rarity,
        path = idPath,
        element = idElement
    )
}
