package com.example.tanorami.info_about_relic.data

import com.example.tanorami.info_about_hero.data.model.Relic

interface InfoAboutRelicRepository {
    suspend fun getRelic(idRelic: Int): Relic
}