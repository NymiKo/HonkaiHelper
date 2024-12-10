package com.example.data.source.additional_stat

import com.example.data.db.entity.AdditionalStatEntity

interface AdditionalStatLocalDataSource {
    suspend fun getAdditionalStats(): List<AdditionalStatEntity>
    suspend fun insertAdditionalStats(additionalStats: List<AdditionalStatEntity>)
}