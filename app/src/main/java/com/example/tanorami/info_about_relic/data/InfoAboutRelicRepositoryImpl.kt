package com.example.tanorami.info_about_relic.data

import com.example.tanorami.data.local.dao.RelicDao
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.info_about_hero.data.model.Relic
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
