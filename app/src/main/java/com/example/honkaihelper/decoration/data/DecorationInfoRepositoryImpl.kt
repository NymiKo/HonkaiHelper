package com.example.honkaihelper.decoration.data

import com.example.honkaihelper.data.local.dao.DecorationDao
import com.example.honkaihelper.info_about_hero.data.model.Decoration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DecorationInfoRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val decorationDao: DecorationDao
): DecorationInfoRepository {
    override suspend fun getDecoration(idDecoration: Int): Decoration = withContext(ioDispatcher) {
        return@withContext decorationDao.getDecoration(idDecoration).toDecoration()
    }
}