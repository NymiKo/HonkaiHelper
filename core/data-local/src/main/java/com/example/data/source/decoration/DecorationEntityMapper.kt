package com.example.data.source.decoration

import com.example.data.db.entity.DecorationEntity
import com.example.data.db.models.decoration.DecorationForBuildRelation
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