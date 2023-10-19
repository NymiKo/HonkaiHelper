package com.example.honkaihelper.heroes.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.image_loader.ImageLoader
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.entity.HeroEntity
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroesListRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
    private val heroesListService: HeroesListService,
    private val imageLoader: ImageLoader
) : HeroesListRepository {

    override suspend fun getHeroesList(): List<Hero> {
        return withContext(ioDispatcher) {
            val cachedHeroes = getHeroesFromCache()
            if (cachedHeroes.isNotEmpty()) {
                return@withContext cachedHeroes
            }

            when(val resultApi = getRemoteHeroesList()) {
                is NetworkResult.Error -> {
                    return@withContext emptyList()
                }
                is NetworkResult.Success -> {
                    val heroes = resultApi.data
                    val localAvatarPaths = downloadAvatars(heroes).await()

                    val localSplashArtsPaths = downloadSplashArts(heroes).await()

                    val heroEntities = heroes.mapIndexed { index, hero ->
                        HeroEntity.toHeroEntity(hero).copy(localAvatarPath = localAvatarPaths[index], localSplashArtPath = localSplashArtsPaths[index])
                    }

                    insertHeroesIntoLocalStorage(heroEntities)

                    return@withContext heroes.sortedBy { it.name }
                }
            }
        }
    }

    private suspend fun getRemoteHeroesList() = handleApi { heroesListService.getHeroesList() }

    private suspend fun getHeroesFromCache(): List<Hero> {
        return heroDao.getHeroes().map { it.toHero() }.sortedBy { it.name }
    }

    private suspend fun insertHeroesIntoLocalStorage(heroEntities: List<HeroEntity>) {
        heroDao.insertHeroes(heroEntities)
    }

    private suspend fun downloadAvatars(heroes: List<Hero>) = withContext(ioDispatcher) {
        async {
            heroes.map { hero ->
                imageLoader.downloadAndSaveImage(hero.avatar, CHILD_HEROES_AVATARS)
            }
        }
    }

    private suspend fun downloadSplashArts(heroes: List<Hero>) = withContext(ioDispatcher) {
        async {
            heroes.map { hero ->
                imageLoader.downloadAndSaveImage(hero.splashArt, CHILD_HEROES_SPLASH_ARTS)
            }
        }
    }

    override suspend fun getAvatar(): NetworkResult<String> {
        return withContext(ioDispatcher) {
            handleApi {
                heroesListService.getAvatar()
            }
        }
    }

    companion object {
        const val CHILD_HEROES_AVATARS = "heroes_avatars"
        const val CHILD_HEROES_SPLASH_ARTS = "heroes_splash_arts"
    }
}