package com.example.tanorami.load_data.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.data.image_loader.ImageLoader
import com.example.tanorami.data.local.dao.AbilityDao
import com.example.tanorami.data.local.dao.BuildDecorationDao
import com.example.tanorami.data.local.dao.BuildRelicDao
import com.example.tanorami.data.local.dao.BuildStatsEquipmentDao
import com.example.tanorami.data.local.dao.BuildWeaponDao
import com.example.tanorami.data.local.dao.DecorationDao
import com.example.tanorami.data.local.dao.EidolonDao
import com.example.tanorami.data.local.dao.ElementDao
import com.example.tanorami.data.local.dao.HeroDao
import com.example.tanorami.data.local.dao.OptimalStatsHeroDao
import com.example.tanorami.data.local.dao.PathDao
import com.example.tanorami.data.local.dao.RelicDao
import com.example.tanorami.data.local.dao.WeaponDao
import com.example.tanorami.data.local.entity.AbilityEntity
import com.example.tanorami.data.local.entity.BuildDecorationEntity
import com.example.tanorami.data.local.entity.BuildRelicEntity
import com.example.tanorami.data.local.entity.BuildStatsEquipmentEntity
import com.example.tanorami.data.local.entity.BuildWeaponEntity
import com.example.tanorami.data.local.entity.DecorationEntity
import com.example.tanorami.data.local.entity.EidolonEntity
import com.example.tanorami.data.local.entity.ElementEntity
import com.example.tanorami.data.local.entity.HeroEntity
import com.example.tanorami.data.local.entity.OptimalStatsHeroEntity
import com.example.tanorami.data.local.entity.PathEntity
import com.example.tanorami.data.local.entity.RelicEntity
import com.example.tanorami.data.local.entity.WeaponEntity
import com.example.tanorami.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class LoadDataRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
    private val pathDao: PathDao,
    private val abilityDao: AbilityDao,
    private val elementDao: ElementDao,
    private val eidolonDao: EidolonDao,
    private val optimalStatsHeroDao: OptimalStatsHeroDao,
    private val buildWeaponDao: BuildWeaponDao,
    private val buildRelicDao: BuildRelicDao,
    private val buildDecorationDao: BuildDecorationDao,
    private val decorationDao: DecorationDao,
    private val relicDao: RelicDao,
    private val buildStatsEquipmentDao: BuildStatsEquipmentDao,
    private val weaponDao: WeaponDao,
    private val loadDataService: LoadDataService,
    private val imageLoader: ImageLoader
) : LoadDataRepository {

    override suspend fun downloadingData(): Boolean = withContext(ioDispatcher) {
        val deferredPaths = async { getPathsHeroes() }
        val deferredHeroes = async { getHeroesList() }
        val deferredElements = async { getElementsHeroes() }
        val deferredAbilities = async { getAbilitiesHeroes() }
        val deferredEidolons = async { getEidolonsHero() }
        val deferredOptimalStats = async { getOptimalStatsList() }
        val deferredBuildWeapon = async { getBuildWeaponsList() }
        val deferredBuildRelic = async { getBuildRelicsList() }
        val deferredBuildDecorations = async { getBuildDecorationsList() }
        val deferredDecorations = async { getDecorationsList() }
        val deferredRelics = async { getRelicsList() }
        val deferredBuildStatsEquipment = async { getStatsEquipmentList() }
        val deferredWeapons = async { getWeaponsList() }

        val results = awaitAll(
            deferredPaths,
            deferredHeroes,
            deferredElements,
            deferredAbilities,
            deferredEidolons,
            deferredOptimalStats,
            deferredBuildWeapon,
            deferredBuildRelic,
            deferredBuildDecorations,
            deferredDecorations,
            deferredRelics,
            deferredBuildStatsEquipment,
            deferredWeapons
        )

        results.all { it }
    }

    private suspend fun getElementsHeroes(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteData { loadDataService.getElementsList() }) {
                is NetworkResult.Error -> {
                    return@withContext false
                }

                is NetworkResult.Success -> {
                    val remoteElements = resultApi.data
                    val localElements = getLocalEntities { elementDao.getElements() }
                    val newElements = remoteElements.filter { element ->
                        localElements.none { it.copy(image = element.image).toElement() == element }
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
            when (val resultApi = getRemoteData { loadDataService.getPathsList() }) {
                is NetworkResult.Error -> {
                    return@withContext false
                }

                is NetworkResult.Success -> {
                    val remotePaths = resultApi.data
                    val localPaths = getLocalEntities { pathDao.getPaths() }
                    val newPaths = remotePaths.filter { path ->
                        localPaths.none { it.copy(image = path.image).toPath() == path }
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
            when (val resultApi = getRemoteData { loadDataService.getAbilitiesList() }) {
                is NetworkResult.Error -> {
                    return@withContext false
                }

                is NetworkResult.Success -> {
                    val remoteAbilities = resultApi.data
                    val localAbilities = getLocalEntities { abilityDao.getAbilities() }
                    val newAbilities = remoteAbilities.filter { ability ->
                        localAbilities.none {
                            it.copy(image = ability.image).toAbility() == ability
                        }
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

    private suspend fun getEidolonsHero(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteData { loadDataService.getEidolonsList() }) {
                is NetworkResult.Error -> {
                    return@withContext false
                }

                is NetworkResult.Success -> {
                    val remoteEidolons = resultApi.data
                    val localEidolons = getLocalEntities { eidolonDao.getEidolons() }
                    val newEidolons = remoteEidolons.filter { eidolons ->
                        localEidolons.none { it.idEidolon == eidolons.idEidolon && it.title == eidolons.title && it.description == eidolons.description && it.idHero == eidolons.idHero }
                    }

                    val localImageEidolons =
                        downloadImages(newEidolons, { it.image }, CHILD_EIDOLONS_IMAGE).await()

                    val eidolonEntities = newEidolons.mapIndexed { index, eidolons ->
                        EidolonEntity.toEidolonEntity(eidolons).copy(
                            image = localImageEidolons[index]
                        )
                    }

                    insertEntitiesIntoLocalStorage(
                        eidolonEntities,
                        eidolonDao::insertEidolons
                    ).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getHeroesList(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteData { loadDataService.getHeroesList() }) {
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

    private suspend fun getOptimalStatsList(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteData { loadDataService.getOptimalStats() }) {
                is NetworkResult.Error -> false
                is NetworkResult.Success -> {
                    val remoteEntities = resultApi.data
                    val localEntities = getLocalEntities { optimalStatsHeroDao.getOptimalStats() }
                    val newEntities = remoteEntities.filter { remoteOptimalStats ->
                        localEntities.none { remoteOptimalStats == it.toOptimalStatsHero() }
                    }

                    insertEntitiesIntoLocalStorage(
                        newEntities.map { OptimalStatsHeroEntity.toOptimalStatsHeroEntity(it) },
                        optimalStatsHeroDao::insertOptimalStatsHero
                    ).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getBuildWeaponsList(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteData { loadDataService.getBuildWeapons() }) {
                is NetworkResult.Error -> false
                is NetworkResult.Success -> {
                    val remoteEntities = resultApi.data
                    val localEntities = getLocalEntities { buildWeaponDao.getBuildWeapon() }
                    val newEntities = remoteEntities.filter { remoteBuildWeapon ->
                        localEntities.none { remoteBuildWeapon == it.toBuildWeapon() }
                    }

                    insertEntitiesIntoLocalStorage(
                        newEntities.map { BuildWeaponEntity.toBuildWeaponEntity(it) },
                        buildWeaponDao::insertBuildWeapon
                    ).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getBuildRelicsList(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteData { loadDataService.getBuildRelics() }) {
                is NetworkResult.Error -> false
                is NetworkResult.Success -> {
                    val remoteEntities = resultApi.data
                    val localEntities = getLocalEntities { buildRelicDao.getBuildRelic() }
                    val newEntities = remoteEntities.filter { remoteBuildRelics ->
                        localEntities.none { remoteBuildRelics == it.toBuildRelic() }
                    }

                    insertEntitiesIntoLocalStorage(
                        newEntities.map { BuildRelicEntity.toBuildRelicEntity(it) },
                        buildRelicDao::insertBuildRelic
                    ).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getBuildDecorationsList(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteData { loadDataService.getBuildDecorations() }) {
                is NetworkResult.Error -> false
                is NetworkResult.Success -> {
                    val remoteEntities = resultApi.data
                    val localEntities =
                        getLocalEntities { buildDecorationDao.getBuildDecorations() }
                    val newEntities = remoteEntities.filter { remoteBuildDecorations ->
                        localEntities.none { remoteBuildDecorations == it.toBuildDecoration() }
                    }

                    insertEntitiesIntoLocalStorage(
                        newEntities.map { BuildDecorationEntity.toBuildDecorationEntity(it) },
                        buildDecorationDao::insertBuildDecorations
                    ).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getDecorationsList(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteData { loadDataService.getDecorations() }) {
                is NetworkResult.Error -> false
                is NetworkResult.Success -> {
                    val remoteEntities = resultApi.data
                    val localEntities = getLocalEntities { decorationDao.getDecorations() }
                    val newEntities = remoteEntities.filter { remoteDecoration ->
                        localEntities.none { remoteDecoration == it.toDecoration() }
                    }

                    val localImageDecorations =
                        downloadImages(newEntities, { it.image }, CHILD_DECORATIONS_IMAGE).await()

                    val decorationEntities = newEntities.mapIndexed { index, decoration ->
                        DecorationEntity.toDecorationEntity(decoration).copy(
                            image = localImageDecorations[index]
                        )
                    }

                    insertEntitiesIntoLocalStorage(
                        decorationEntities,
                        decorationDao::insertDecorations
                    ).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getRelicsList(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteData { loadDataService.getRelics() }) {
                is NetworkResult.Error -> false
                is NetworkResult.Success -> {
                    val remoteEntities = resultApi.data
                    val localEntities = getLocalEntities { relicDao.getRelics() }
                    val newEntities = remoteEntities.filter { remoteRelic ->
                        localEntities.none { remoteRelic == it.toRelic() }
                    }

                    val localImageRelics =
                        downloadImages(newEntities, { it.image }, CHILD_RELICS_IMAGE).await()

                    val relicEntities = newEntities.mapIndexed { index, relic ->
                        RelicEntity.toRelicEntity(relic).copy(
                            image = localImageRelics[index]
                        )
                    }

                    insertEntitiesIntoLocalStorage(
                        relicEntities,
                        relicDao::insertRelics
                    ).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getStatsEquipmentList(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteData { loadDataService.getStatsEquipment() }) {
                is NetworkResult.Error -> false
                is NetworkResult.Success -> {
                    val remoteEntities = resultApi.data
                    val localEntities =
                        getLocalEntities { buildStatsEquipmentDao.getBuildStatsEquipment() }
                    val newEntities = remoteEntities.filter { remoteStatsEquipment ->
                        localEntities.none { remoteStatsEquipment == it.toBuildStatsEquipment() }
                    }

                    insertEntitiesIntoLocalStorage(
                        newEntities.map { BuildStatsEquipmentEntity.toBuildStatsEquipmentEntity(it) },
                        buildStatsEquipmentDao::insertBuildStatsEquipment
                    ).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun getWeaponsList(): Boolean {
        return withContext(ioDispatcher) {
            when (val resultApi = getRemoteData { loadDataService.getWeapons() }) {
                is NetworkResult.Error -> false
                is NetworkResult.Success -> {
                    val remoteEntities = resultApi.data
                    val localEntities = getLocalEntities { weaponDao.getWeapons() }
                    val newEntities = remoteEntities.filter { weapon ->
                        localEntities.none { weapon == it.toWeapon() }
                    }

                    val localImageWeapons =
                        downloadImages(newEntities, { it.image }, CHILD_WEAPONS_IMAGE).await()

                    val weaponEntities = newEntities.mapIndexed { index, weapon ->
                        WeaponEntity.toWeaponEntity(weapon).copy(
                            image = localImageWeapons[index]
                        )
                    }

                    insertEntitiesIntoLocalStorage(
                        weaponEntities,
                        weaponDao::insertWeapons
                    ).join()

                    return@withContext true
                }
            }
        }
    }

    private suspend fun <T> getRemoteData(getData: suspend () -> Response<T>) =
        handleApi { getData() }

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
        const val CHILD_EIDOLONS_IMAGE = "eidolons_image"
        const val CHILD_DECORATIONS_IMAGE = "decorations_image"
        const val CHILD_RELICS_IMAGE = "relics_image"
        const val CHILD_WEAPONS_IMAGE = "weapons_image"
    }
}