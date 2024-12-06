package com.example.data.source.relic.mapper

import com.example.common.RelicModel
import com.example.data.db.entity.RelicEntity
import com.example.data.db.models.relic.RelicForBuildRelation
import com.example.domain.repository.relic.RelicForBuildModel

fun RelicEntity.toRelicModel() = RelicModel(
    idRelic,
    title,
    descriptionTwoParts,
    descriptionFourParts,
    image,
)

fun RelicForBuildRelation.toRelicForBuildModel() = RelicForBuildModel(
    idRelic,
    top,
    relic.toRelicModel(),
)