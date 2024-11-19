package com.example.tanorami.info_about_decoration.data

import com.example.data.db.dao.DecorationDao
import com.example.domain.di.IODispatcher
import com.example.domain.repository.decoration.DecorationModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoAboutDecorationRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val decorationDao: DecorationDao
): InfoAboutDecorationRepository {
    override suspend fun getDecoration(idDecoration: Int): DecorationModel =
        withContext(ioDispatcher) {
        return@withContext decorationDao.getDecoration(idDecoration).toDecoration()
    }
}