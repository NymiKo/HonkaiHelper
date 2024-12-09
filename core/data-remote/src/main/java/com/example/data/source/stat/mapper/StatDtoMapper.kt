package com.example.data.source.stat.mapper

import com.example.data.remote.api.stat.model.StatDto
import com.example.domain.repository.stat.models.StatModel

fun StatDto.toStatModel() = StatModel(idStat, statName)