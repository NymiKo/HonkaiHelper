package com.example.data.source.ability

import com.example.data.db.entity.AbilityEntity
import com.example.domain.repository.ability.AbilityModel

fun AbilityEntity.toAbilityModel() = AbilityModel(
    idAbility,
    type,
    title,
    description,
    image,
    idHero,
)