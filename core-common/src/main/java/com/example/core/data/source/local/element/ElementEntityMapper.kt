package com.example.core.data.source.local.element

import com.example.data.local.entity.ElementEntity
import com.example.domain.repository.element.ElementModel

fun ElementEntity.toElementModel() = ElementModel(
    idElement,
    title,
    image,
)