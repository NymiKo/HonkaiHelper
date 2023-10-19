package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.honkaihelper.data.local.contract.RoomContract
import com.example.honkaihelper.heroes.data.model.Hero

@Entity(
    tableName = RoomContract.tableHeroes
)
data class HeroEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
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
            rarity = hero.rarity,
            idPath = hero.path,
            idElement = hero.element
        )
    }

    fun toHero() = Hero(
        id = id,
        name = name,
        avatar = localAvatarPath,
        splashArt = localSplashArtPath,
        rarity = rarity,
        path = idPath,
        element = idElement
    )
}
