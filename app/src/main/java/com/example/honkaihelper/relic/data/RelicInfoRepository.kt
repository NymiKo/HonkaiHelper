package com.example.honkaihelper.relic.data

import com.example.honkaihelper.info_about_hero.data.model.Relic

interface RelicInfoRepository {
    suspend fun getRelic(idRelic: Int): Relic
}