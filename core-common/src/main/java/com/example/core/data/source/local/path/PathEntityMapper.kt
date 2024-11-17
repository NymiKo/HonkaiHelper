package com.example.core.data.source.local.path

import com.example.data.local.entity.PathEntity
import com.example.domain.repository.path.PathModel

fun PathEntity.toPathModel() = PathModel(
    idPath,
    title,
    image,
)