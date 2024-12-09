package com.example.data.source.stat.mapper

import com.example.data.db.entity.StatEntity
import com.example.domain.repository.stat.models.StatModel

fun StatEntity.toStatModel() = StatModel(idStat, statName)

fun StatModel.toStatEntity() = StatEntity(idStat, statName)