package com.example.tanorami.relic.data

import com.example.tanorami.data.local.dao.RelicDao
import com.example.tanorami.info_about_hero.data.model.Relic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RelicInfoRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val relicDao: RelicDao
): RelicInfoRepository {
    override suspend fun getRelic(idRelic: Int): Relic = withContext(ioDispatcher) {
        return@withContext relicDao.getRelic(idRelic).toRelic()
    }
}
