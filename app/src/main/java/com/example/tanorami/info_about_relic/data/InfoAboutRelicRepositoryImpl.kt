package com.example.tanorami.info_about_relic.data

import com.example.core.database.dao.RelicDao
import com.example.core.di.IODispatcher
import com.example.core.domain.repository.relic.Relic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoAboutRelicRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val relicDao: RelicDao
) : InfoAboutRelicRepository {
    override suspend fun getRelic(idRelic: Int): Relic = withContext(ioDispatcher) {
        return@withContext relicDao.getRelic(idRelic).toRelic()
    }
}
