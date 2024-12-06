package com.example.data.source.relic

import com.example.data.db.dao.RelicDao
import com.example.data.db.entity.RelicEntity
import com.example.domain.di.DispatcherIo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RelicLocalDataSourceImpl @Inject constructor(
    private val relicDao: RelicDao,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : RelicLocalDataSource {
    override suspend fun getRelics(): List<RelicEntity> {
        return withContext(ioDispatcher) {
            relicDao.getRelics()
        }
    }

    override suspend fun getRelic(idRelic: Int): RelicEntity {
        return withContext(ioDispatcher) {
            relicDao.getRelic(idRelic)
        }
    }

    override suspend fun insertRelics(relics: List<RelicEntity>) {
        return withContext(ioDispatcher) {
            relicDao.insertRelics(relics)
        }
    }
}