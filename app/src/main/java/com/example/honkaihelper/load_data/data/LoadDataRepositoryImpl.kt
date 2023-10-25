package com.example.honkaihelper.load_data.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.image_loader.ImageLoader
import com.example.honkaihelper.data.local.dao.AbilityDao
import com.example.honkaihelper.data.local.dao.ElementDao
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.dao.PathDao
import com.example.honkaihelper.data.local.entity.AbilityEntity
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
    private val abilityDao: AbilityDao,
    private val elementDao: ElementDao,
    private val loadDataService: LoadDataService,
    private val imageLoader: ImageLoader
) : LoadDataRepository {

    override suspend fun downloadingData(): Boolean {
        return getPathsHeroes() && getHeroesList() && getElementsHeroes() && getAbilitiesHeroes()
    }

    private suspend fun getElementsHeroes(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteElementsList()) {
                is NetworkResult.Error -> {
                    return@withContext false
                }

                is NetworkResult.Success -> {
                    val remoteElements = resultApi.data
                    val localElements = getLocalEntities { elementDao.getElements() }
                    val newElements = remoteElements.filter { element ->
                        localElements.none { it.idElement == element.idElement && it.title == element.image }
                    }

                    val localImageElements =
                        downloadImages(newElements, { it.image }, CHILD_ELEMENTS_IMAGE).await()

                    val elementEntities = newElements.mapIndexed { index, element ->
                        ElementEntity.toElementEntity(element).copy(
                            image = localImageElements[index]
                        )
                    }

                    insertEntitiesIntoLocalStorage(
                        elementEntities,
                        elementDao::insertElements
                    ).join()

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
                    val localPaths = getLocalEntities { pathDao.getPaths() }
                    val newPaths = remotePaths.filter { path ->
                        localPaths.none { it.idPath == path.idPath && it.title == path.title }
                    }

                    val localImagePaths =
                        downloadImages(newPaths, { it.image }, CHILD_PATHS_IMAGE).await()

                    val pathEntities = newPaths.mapIndexed { index, path ->
                        PathEntity.toPathEntity(path).copy(
                            image = localImagePaths[index]
                        )
                    }

                    insertEntitiesIntoLocalStorage(pathEntities, pathDao::insertPaths).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getAbilitiesHeroes(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteAbilitiesList()) {
                is NetworkResult.Error -> {
                    return@withContext false
                }

                is NetworkResult.Success -> {
                    val remoteAbilities = resultApi.data
                    val localAbilities = getLocalEntities { abilityDao.getAbilities() }
                    val newAbilities = remoteAbilities.filter { ability ->
                        localAbilities.none { it.idAbility == ability.idAbility && it.title == ability.title && it.description == ability.description && it.idHero == ability.idHero }
                    }

                    val localImageAbilities =
                        downloadImages(newAbilities, { it.image }, CHILD_ABILITIES_IMAGE).await()

                    val abilityEntities = newAbilities.mapIndexed { index, ability ->
                        AbilityEntity.toAbilityEntity(ability).copy(
                            image = localImageAbilities[index]
                        )
                    }

                    insertEntitiesIntoLocalStorage(
                        abilityEntities,
                        abilityDao::insertAbilities
                    ).join()

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
                    val localHeroes = getLocalEntities { heroDao.getHeroes() }
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

                    insertEntitiesIntoLocalStorage(heroEntities, heroDao::insertHeroes).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getRemoteHeroesList() = handleApi { loadDataService.getHeroesList() }

    private suspend fun getRemotePathsList() = handleApi { loadDataService.getPathsList() }

    private suspend fun getRemoteElementsList() = handleApi { loadDataService.getElementsList() }

    private suspend fun getRemoteAbilitiesList() = handleApi { loadDataService.getAbilitiesList() }

    private suspend fun <T> insertEntitiesIntoLocalStorage(
        entityList: List<T>,
        insertFunction: suspend (List<T>) -> Unit
    ): Job {
        return CoroutineScope(ioDispatcher).launch {
            insertFunction(entityList)
        }
    }

    private suspend fun <T> getLocalEntities(query: suspend () -> List<T>): List<T> {
        return withContext(ioDispatcher) {
            query()
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
        const val CHILD_ABILITIES_IMAGE = "abilities_image"
    }
}