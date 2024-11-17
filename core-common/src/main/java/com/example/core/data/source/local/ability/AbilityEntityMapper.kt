package com.example.core.data.source.local.ability

import com.example.data.local.entity.AbilityEntity
import com.example.domain.repository.ability.AbilityModel

fun AbilityEntity.toAbilityModel() = AbilityModel(
    idAbility,
    type,
    title,
    description,
    image,
    idHero,
)