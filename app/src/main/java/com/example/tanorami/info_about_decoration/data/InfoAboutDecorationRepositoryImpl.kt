package com.example.tanorami.info_about_decoration.data

import com.example.core.database.dao.DecorationDao
import com.example.core.di.IODispatcher
import com.example.core.domain.repository.decoration.Decoration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoAboutDecorationRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val decorationDao: DecorationDao
): InfoAboutDecorationRepository {
    override suspend fun getDecoration(idDecoration: Int): Decoration = withContext(ioDispatcher) {
        return@withContext decorationDao.getDecoration(idDecoration).toDecoration()
    }
}