package com.example.data.source.additional_stat.mapper

import com.example.data.db.entity.AdditionalStatEntity
import com.example.domain.repository.additional_stat.model.AdditionalStatModel

fun AdditionalStatEntity.toAdditionalStatModel() =
    AdditionalStatModel(idAdditionalStat, idStat, idHero)

fun AdditionalStatModel.toAdditionalStatEntity() =
    AdditionalStatEntity(idAdditionalStat, idStat, idHero)