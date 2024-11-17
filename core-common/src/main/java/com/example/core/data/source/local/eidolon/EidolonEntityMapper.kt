package com.example.core.data.source.local.eidolon

import com.example.data.local.entity.EidolonEntity
import com.example.domain.repository.eidolon.EidolonModel

fun EidolonEntity.toEidolonModel() = EidolonModel(
    idEidolon,
    title,
    description,
    image,
    idHero,
)