package com.example.data.source.eidolon

import com.example.data.db.entity.EidolonEntity
import com.example.domain.repository.eidolon.EidolonModel

fun EidolonEntity.toEidolonModel() = EidolonModel(
    idEidolon,
    title,
    description,
    image,
    idHero,
)