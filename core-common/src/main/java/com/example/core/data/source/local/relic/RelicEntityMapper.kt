package com.example.core.data.source.local.relic

import com.example.data.local.entity.RelicEntity
import com.example.data.local.models.relic.RelicForBuildRelation
import com.example.domain.repository.relic.RelicForBuildModel
import com.example.domain.repository.relic.RelicModel

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