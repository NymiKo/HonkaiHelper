package com.example.tanorami.info_about_relic.data

import com.example.core.di.IODispatcher
import com.example.core.local.dao.RelicDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoAboutRelicRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val relicDao: RelicDao
) : InfoAboutRelicRepository {
    override suspend fun getRelic(idRelic: Int): com.example.domain.repository.relic.Relic =
        withContext(ioDispatcher) {
        return@withContext relicDao.getRelic(idRelic).toRelic()
    }
}
