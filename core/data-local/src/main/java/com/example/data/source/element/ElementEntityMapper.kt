package com.example.data.source.element

import com.example.data.db.entity.ElementEntity
import com.example.domain.repository.element.ElementModel

fun ElementEntity.toElementModel() = ElementModel(
    idElement,
    title,
    image,
)