package com.example.tanorami.heroes.data

import com.example.tanorami.core.data.source.local.heroes.HeroLocalDataSource
import com.example.tanorami.core.di.IODispatcher
import com.example.tanorami.heroes.data.model.Hero
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class HeroesListRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val heroLocalDataSource: HeroLocalDataSource,
) : HeroesListRepository {
    override suspend fun getHeroesList(): List<Hero> {
        return heroLocalDataSource.getHeroesList().sortedBy { it.name }
    }
}