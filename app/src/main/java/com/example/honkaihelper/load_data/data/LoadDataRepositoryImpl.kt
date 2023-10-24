package com.example.honkaihelper.load_data.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.image_loader.ImageLoader
import com.example.honkaihelper.data.local.dao.ElementDao
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.dao.PathDao
import com.example.honkaihelper.data.local.entity.ElementEntity
import com.example.honkaihelper.data.local.entity.HeroEntity
import com.example.honkaihelper.data.local.entity.PathEntity
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
    private val pathDao: PathDao,
    private val elementDao: ElementDao,
    private val loadDataService: LoadDataService,
    private val imageLoader: ImageLoader
) : LoadDataRepository {

    override suspend fun downloadingData(): Boolean {
        return getPathsHeroes() && getHeroesList() && getElementsHeroes()
    }

    private suspend fun getElementsHeroes(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteElementsList()) {
                is NetworkResult.Error -> {
                    return@withContext false
                }

                is NetworkResult.Success -> {
                    val remoteElements = resultApi.data
                    val localElements = getLocalElements()
                    val newElements = remoteElements.filter { element ->
                        localElements.none { it.idElement == element.idElement && it.title == element.image }
                    }

                    val localImageElements =
                        downloadImages(newElements, { it.image }, CHILD_ELEMENTS_IMAGE).await()

                    val elementEntities = newElements.mapIndexed { index, element ->
                        ElementEntity.toPathEntity(element).copy(
                            image = localImageElements[index]
                        )
                    }

                    insertElementsIntoLocalStorage(elementEntities).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getPathsHeroes(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemotePathsList()) {
                is NetworkResult.Error -> {
                    return@withContext false
                }

                is NetworkResult.Success -> {
                    val remotePaths = resultApi.data
                    val localPaths = getLocalPaths()
                    val newPaths = remotePaths.filter { path ->
                        localPaths.none { it.idPath == path.idPath && it.title == path.title }
                    }

                    val localImagePaths =
                        downloadImages(newPaths, { it.image }, CHILD_PATHS_IMAGE).await()

                    val heroEntities = newPaths.mapIndexed { index, path ->
                        PathEntity.toPathEntity(path).copy(
                            image = localImagePaths[index]
                        )
                    }

                    insertPathsIntoLocalStorage(heroEntities).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getHeroesList(): Boolean {
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

    private suspend fun getRemoteHeroesList() = handleApi { loadDataService.getHeroesList() }

    private suspend fun getRemotePathsList() = handleApi { loadDataService.getPathsList() }

    private suspend fun getRemoteElementsList() = handleApi { loadDataService.getElementsList() }

    private suspend fun insertHeroesIntoLocalStorage(heroEntities: List<HeroEntity>): Job {
        return CoroutineScope(ioDispatcher).launch {
            heroDao.insertHeroes(heroEntities)
        }
    }

    private suspend fun insertPathsIntoLocalStorage(pathEntity: List<PathEntity>): Job {
        return CoroutineScope(ioDispatcher).launch {
            pathDao.insertPaths(pathEntity)
        }
    }

    private suspend fun insertElementsIntoLocalStorage(elementEntity: List<ElementEntity>): Job {
        return CoroutineScope(ioDispatcher).launch {
            elementDao.insertElements(elementEntity)
        }
    }

    private suspend fun getLocalHeroes(): List<HeroEntity> {
        return withContext(ioDispatcher) {
            heroDao.getHeroes()
        }
    }

    private suspend fun getLocalPaths(): List<PathEntity> {
        return withContext(ioDispatcher) {
            pathDao.getPaths()
        }
    }

    private suspend fun getLocalElements(): List<ElementEntity> {
        return withContext(ioDispatcher) {
            elementDao.getElements()
        }
    }

    private suspend fun <T> downloadImages(
        list: List<T>, propertySelector: (T) -> String, folder: String
    ) = withContext(ioDispatcher) {
        async {
            list.mapIndexed { index, item ->
                val fileName = "hero_${index}_${System.currentTimeMillis()}.webp"
                imageLoader.downloadAndSaveImage(propertySelector(item), folder, fileName)
            }
        }
    }

    companion object {
        const val CHILD_HEROES_AVATARS = "heroes_avatars"
        const val CHILD_HEROES_SPLASH_ARTS = "heroes_splash_arts"
        const val CHILD_PATHS_IMAGE = "paths_image"
        const val CHILD_ELEMENTS_IMAGE = "elements_image"
    }
}