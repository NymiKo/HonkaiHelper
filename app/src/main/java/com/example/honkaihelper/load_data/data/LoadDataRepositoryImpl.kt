package com.example.honkaihelper.load_data.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.image_loader.ImageLoader
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.entity.HeroEntity
import com.example.honkaihelper.heroes.data.HeroesListService
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadDataRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
    private val heroesListService: HeroesListService,
    private val imageLoader: ImageLoader
) : LoadDataRepository {

    override suspend fun getHeroesList(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteHeroesList()) {
                is NetworkResult.Error -> {
                    return@withContext false
                }

                is NetworkResult.Success -> {
                    val remoteHeroes = resultApi.data
                    val localHeroes = getLocalHeroes()
                    val newHeroes = remoteHeroes.filter { hero ->
                        localHeroes.none { it.id == hero.id && it.name == hero.name && it.rarity == hero.rarity && it.idPath == hero.path && it.idElement == hero.element }
                    }

                    val localAvatarPaths =
                        downloadImages(newHeroes, { it.avatar }, CHILD_HEROES_AVATARS).await()

                    val localSplashArtsPaths = downloadImages(
                        newHeroes, { it.splashArt }, CHILD_HEROES_SPLASH_ARTS
                    ).await()

                    val heroEntities = newHeroes.mapIndexed { index, hero ->
                        HeroEntity.toHeroEntity(hero).copy(
                            localAvatarPath = localAvatarPaths[index],
                            localSplashArtPath = localSplashArtsPaths[index]
                        )
                    }

                    insertHeroesIntoLocalStorage(heroEntities).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getRemoteHeroesList() = handleApi { heroesListService.getHeroesList() }

    private suspend fun insertHeroesIntoLocalStorage(heroEntities: List<HeroEntity>): Job {
        return CoroutineScope(ioDispatcher).launch {
            heroDao.insertHeroes(heroEntities)
        }
    }

    private suspend fun getLocalHeroes(): List<HeroEntity> {
        return withContext(ioDispatcher) {
            heroDao.getHeroes()
        }
    }

    private suspend fun downloadImages(
        heroes: List<Hero>, propertySelector: (Hero) -> String, folder: String
    ) = withContext(ioDispatcher) {
        async {
            heroes.map { hero ->
                val fileName = "hero_${hero.id}_${System.currentTimeMillis()}.webp"
                imageLoader.downloadAndSaveImage(propertySelector(hero), folder, fileName)
            }
        }
    }

    companion object {
        const val CHILD_HEROES_AVATARS = "heroes_avatars"
        const val CHILD_HEROES_SPLASH_ARTS = "heroes_splash_arts"
    }
}