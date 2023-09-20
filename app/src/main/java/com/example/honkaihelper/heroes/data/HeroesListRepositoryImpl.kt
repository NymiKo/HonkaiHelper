package com.example.honkaihelper.heroes.data

import android.util.Log
import com.example.honkaihelper.models.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception

class HeroesListRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val heroesListService: HeroesListService
): HeroesListRepository {

    override suspend fun getHeroesList(): List<Hero> {
        return withContext(ioDispatcher) {
            return@withContext try {
                heroesListService.getHeroesList()
            } catch (e: Exception) {
                // TODO: Добавить обработку ошибок
                emptyList<Hero>()
            }
        }
    }
}