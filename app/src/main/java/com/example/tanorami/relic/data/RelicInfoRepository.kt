package com.example.tanorami.relic.data

import com.example.tanorami.info_about_hero.data.model.Relic

interface RelicInfoRepository {
    suspend fun getRelic(idRelic: Int): Relic
}