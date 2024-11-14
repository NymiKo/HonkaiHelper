package com.example.tanorami.info_about_relic.data

import com.example.core.domain.repository.relic.Relic

interface InfoAboutRelicRepository {
    suspend fun getRelic(idRelic: Int): Relic
}