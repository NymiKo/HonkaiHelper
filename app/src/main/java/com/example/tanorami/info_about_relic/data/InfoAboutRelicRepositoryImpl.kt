package com.example.tanorami.info_about_relic.data

import com.example.data.db.dao.RelicDao
import com.example.domain.di.DispatcherIo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoAboutRelicRepositoryImpl @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val relicDao: RelicDao,
) : InfoAboutRelicRepository {
    override suspend fun getRelic(idRelic: Int): com.example.common.RelicModel =
        withContext(ioDispatcher) {
        return@withContext relicDao.getRelic(idRelic).toRelic()
    }
}
