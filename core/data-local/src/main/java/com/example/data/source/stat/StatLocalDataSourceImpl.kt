package com.example.data.source.stat

import com.example.data.db.dao.StatDao
import com.example.data.db.entity.StatEntity
import com.example.domain.di.DispatcherIo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StatLocalDataSourceImpl @Inject constructor(
    private val statDao: StatDao,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : StatLocalDataSource {
    override suspend fun getStatsList(): List<StatEntity> {
        return withContext(ioDispatcher) {
            statDao.getStatsList()
        }
    }

    override suspend fun insertHeroesList(statsList: List<StatEntity>) {
        return withContext(ioDispatcher) {
            statDao.insertStats(statsList)
        }
    }
}