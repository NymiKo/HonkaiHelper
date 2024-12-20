package com.example.tanorami.info_about_decoration.data

import com.example.data.db.dao.DecorationDao
import com.example.domain.di.DispatcherIo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoAboutDecorationRepositoryImpl @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val decorationDao: DecorationDao,
): InfoAboutDecorationRepository {
    override suspend fun getDecoration(idDecoration: Int): com.example.common.DecorationModel =
        withContext(ioDispatcher) {
        return@withContext decorationDao.getDecoration(idDecoration).toDecoration()
    }
}