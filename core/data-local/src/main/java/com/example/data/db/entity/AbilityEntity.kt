package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.db.contract.RoomContract
import com.example.domain.repository.ability.AbilityModel

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
        fun toAbilityEntity(ability: AbilityModel) = AbilityEntity(
            idAbility = ability.idAbility,
            type = ability.type,
            title = ability.title,
            description = ability.description,
            idHero = ability.idHero
        )
    }

    fun toAbility() = AbilityModel(
        idAbility = idAbility,
        type = type,
        title = title,
        description = description,
        image = image,
        idHero = idHero
    )
}