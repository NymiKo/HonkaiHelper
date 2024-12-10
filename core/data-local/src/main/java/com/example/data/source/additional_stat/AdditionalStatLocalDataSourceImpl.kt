package com.example.data.source.additional_stat

import com.example.data.db.dao.AdditionalStatDao
import com.example.data.db.entity.AdditionalStatEntity
import com.example.domain.di.DispatcherIo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdditionalStatLocalDataSourceImpl @Inject constructor(
    private val additionalStatDao: AdditionalStatDao,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : AdditionalStatLocalDataSource {
    override suspend fun getAdditionalStats(): List<AdditionalStatEntity> {
        return withContext(ioDispatcher) {
            additionalStatDao.getAdditionalStats()
        }
    }

    override suspend fun insertAdditionalStats(additionalStats: List<AdditionalStatEntity>) {
        return withContext(ioDispatcher) {
            additionalStatDao.insertAdditionalStats(additionalStats)
        }
    }
}