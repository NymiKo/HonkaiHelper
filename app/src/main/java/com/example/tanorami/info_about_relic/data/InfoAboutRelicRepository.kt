package com.example.tanorami.info_about_relic.data

interface InfoAboutRelicRepository {
    suspend fun getRelic(idRelic: Int): com.example.domain.repository.relic.Relic
}