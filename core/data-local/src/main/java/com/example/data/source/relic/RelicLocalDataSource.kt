package com.example.data.source.relic

import com.example.data.db.entity.RelicEntity

interface RelicLocalDataSource {
    suspend fun getRelics(): List<RelicEntity>
    suspend fun getRelic(idRelic: Int): RelicEntity
    suspend fun insertRelics(relics: List<RelicEntity>)
}