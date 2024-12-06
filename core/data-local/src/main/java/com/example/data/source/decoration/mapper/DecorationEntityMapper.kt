package com.example.data.source.decoration.mapper

import com.example.common.DecorationModel
import com.example.data.db.entity.DecorationEntity
import com.example.data.db.models.decoration.DecorationForBuildRelation
import com.example.domain.repository.decoration.DecorationForBuildModel

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