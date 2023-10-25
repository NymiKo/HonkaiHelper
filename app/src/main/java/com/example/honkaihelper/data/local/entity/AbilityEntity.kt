package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import com.example.honkaihelper.base_build_hero.data.model.Ability
import com.example.honkaihelper.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableAbilities)
data class AbilityEntity(
    val idAbility: Int,
    val title: String,
    val description: String,
    val image: String = "",
    val idHero: Int
) {
    companion object {
        fun toAbilityEntity(ability: Ability) = AbilityEntity(
            idAbility = ability.idAbility,
            title = ability.title,
            description = ability.description,
            idHero = ability.idHero
        )
    }

    fun toAbility() = Ability(
        idAbility = idAbility,
        title = title,
        description = description,
        image = image,
        idHero = idHero
    )
}