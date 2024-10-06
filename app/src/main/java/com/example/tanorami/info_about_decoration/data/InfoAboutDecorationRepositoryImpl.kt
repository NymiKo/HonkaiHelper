package com.example.tanorami.info_about_decoration.data

import com.example.tanorami.data.local.dao.DecorationDao
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.info_about_hero.data.model.Decoration
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