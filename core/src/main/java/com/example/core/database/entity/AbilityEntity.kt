package com.example.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.database.contract.RoomContract
import com.example.core.domain.repository.ability.Ability

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