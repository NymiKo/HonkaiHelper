package com.example.core.data.source.local.decoration

import com.example.data.local.entity.DecorationEntity
import com.example.data.local.models.decoration.DecorationForBuildRelation
import com.example.domain.repository.decoration.DecorationForBuildModel
import com.example.domain.repository.decoration.DecorationModel

fun DecorationEntity.toDecorationModel() = DecorationModel(
    idDecoration,
    title,
    description,
    image,
)

fun DecorationForBuildRelation.toDecorationForBuildModel() = DecorationForBuildModel(
    idDecoration,
    top,
    decoration.toDecorationModel(),
)