package com.example.data.source.relic

import com.example.data.db.entity.RelicEntity
import com.example.data.db.models.relic.RelicForBuildRelation
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