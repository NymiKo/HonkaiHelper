package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.honkaihelper.info_about_hero.data.model.Ability
import com.example.honkaihelper.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableAbilities)
data class AbilityEntity(
    @PrimaryKey(autoGenerate = false)
    val idAbility: Int,
    val type: String,
    val title: String,
    val description: String,
    val image: String = "",
    val idHero: Int
) {
    companion object {
        fun toAbilityEntity(ability: Ability) = AbilityEntity(
            idAbility = ability.idAbility,
            type = ability.type,
            title = ability.title,
            description = ability.description,
            idHero = ability.idHero
        )
    }

    fun toAbility() = Ability(
        idAbility = idAbility,
        type = type,
        title = title,
        description = description,
        image = image,
        idHero = idHero
    )
}