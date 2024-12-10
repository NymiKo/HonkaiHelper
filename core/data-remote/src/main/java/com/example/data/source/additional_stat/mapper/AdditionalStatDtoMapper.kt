package com.example.data.source.additional_stat.mapper

import com.example.data.remote.api.additional_stat.model.AdditionalStatDto
import com.example.domain.repository.additional_stat.model.AdditionalStatModel

fun AdditionalStatDto.toAdditionalStatModel() =
    AdditionalStatModel(idAdditionalStat, idStat, idHero)