package com.example.tanorami.info_about_relic.data

import com.example.domain.repository.relic.RelicModel

interface InfoAboutRelicRepository {
    suspend fun getRelic(idRelic: Int): RelicModel
}