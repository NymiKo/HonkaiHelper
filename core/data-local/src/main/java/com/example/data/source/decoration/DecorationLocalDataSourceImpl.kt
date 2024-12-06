package com.example.data.source.decoration

import com.example.data.db.dao.DecorationDao
import com.example.data.db.entity.DecorationEntity
import com.example.domain.di.DispatcherIo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DecorationLocalDataSourceImpl @Inject constructor(
    private val decorationDao: DecorationDao,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : DecorationLocalDataSource {
    override suspend fun getDecorations(): List<DecorationEntity> {
        return withContext(ioDispatcher) {
            decorationDao.getDecorations()
        }
    }

    override suspend fun getDecoration(idDecoration: Int): DecorationEntity {
        return withContext(ioDispatcher) {
            decorationDao.getDecoration(idDecoration)
        }
    }

    override suspend fun insertDecorations(decorations: List<DecorationEntity>) {
        return withContext(ioDispatcher) {
            decorationDao.insertDecorations(decorations)
        }
    }
}