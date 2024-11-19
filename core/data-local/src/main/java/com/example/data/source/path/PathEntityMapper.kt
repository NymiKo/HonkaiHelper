package com.example.data.source.path

import com.example.data.db.entity.PathEntity
import com.example.domain.repository.path.PathModel

fun PathEntity.toPathModel() = PathModel(
    idPath,
    title,
    image,
)