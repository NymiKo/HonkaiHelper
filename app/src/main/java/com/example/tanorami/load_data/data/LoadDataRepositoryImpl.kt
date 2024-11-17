package com.example.tanorami.load_data.data

import com.example.core.di.IODispatcher
import com.example.core.local.dao.AbilityDao
import com.example.core.local.dao.BuildDecorationDao
import com.example.core.local.dao.BuildRelicDao
import com.example.core.local.dao.BuildStatsEquipmentDao
import com.example.core.local.dao.BuildWeaponDao
import com.example.core.local.dao.DecorationDao
import com.example.core.local.dao.EidolonDao
import com.example.core.local.dao.ElementDao
import com.example.core.local.dao.HeroDao
import com.example.core.local.dao.OptimalStatsHeroDao
import com.example.core.local.dao.PathDao
import com.example.core.local.dao.RelicDao
import com.example.core.local.dao.WeaponDao
import com.example.core.local.entity.AbilityEntity
import com.example.core.local.entity.BuildDecorationEntity
import com.example.core.local.entity.BuildRelicEntity
import com.example.core.local.entity.BuildStatsEquipmentEntity
import com.example.core.local.entity.BuildWeaponEntity
import com.example.core.local.entity.DecorationEntity
import com.example.core.local.entity.EidolonEntity
import com.example.core.local.entity.ElementEntity
import com.example.core.local.entity.HeroEntity
import com.example.core.local.entity.OptimalStatsHeroEntity
import com.example.core.local.entity.PathEntity
import com.example.core.local.entity.RelicEntity
import com.example.core.local.entity.WeaponEntity
import com.example.core.network.NetworkResult
import com.example.core.network.handleApi
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
    private val fileManager: FileManager
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
                    val localElements = elementDao.getElements()
                    val newElements = remoteElements.filter { remoteElement ->
                        localElements.none { localElement ->
                            localElement.copy(image = com.example.domain.repository.element.Element.image)
                                .toElement() == remoteElement
                        }
                    }

                    val localImageElements =
                        downloadImages(
                            newElements,
                            { com.example.domain.repository.element.Element.image },
                            CHILD_ELEMENTS_IMAGE
                        ).await()

                    val elementEntities = newElements.mapIndexed { index, element ->
                        ElementEntity.toElementEntity(element).copy(
                            image = localImageElements[index]
                        )
                    }

                    elementDao.insertElements(elementEntities)

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
                    val localPaths = pathDao.getPaths()
                    val newPaths = remotePaths.filter { path ->
                        localPaths.none {
                            it.copy(image = com.example.domain.repository.path.Path.image)
                                .toPath() == path
                        }
                    }

                    val localImagePaths =
                        downloadImages(
                            newPaths,
                            { com.example.domain.repository.path.Path.image },
                            CHILD_PATHS_IMAGE
                        ).await()

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
                    val localAbilities = abilityDao.getAbilities()
                    val newAbilities = remoteAbilities.filter { ability ->
                        localAbilities.none {
                            it.copy(image = com.example.domain.repository.ability.Ability.image)
                                .toAbility() == ability
                        }
                    }

                    val localImageAbilities =
                        downloadImages(
                            newAbilities,
                            { com.example.domain.repository.ability.Ability.image },
                            CHILD_ABILITIES_IMAGE
                        ).await()

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
                    val localEidolons = eidolonDao.getEidolons()
                    val newEidolons = remoteEidolons.filter { eidolons ->
                        localEidolons.none { it.idEidolon == com.example.domain.repository.eidolon.Eidolon.idEidolon && it.title == com.example.domain.repository.eidolon.Eidolon.title && it.description == com.example.domain.repository.eidolon.Eidolon.description && it.idHero == com.example.domain.repository.eidolon.Eidolon.idHero }
                    }

                    val localImageEidolons =
                        downloadImages(
                            newEidolons,
                            { com.example.domain.repository.eidolon.Eidolon.image },
                            CHILD_EIDOLONS_IMAGE
                        ).await()

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
                    val localHeroes = heroDao.getHeroesList()
                    val newHeroes = remoteHeroes.filter { hero ->
                        localHeroes.none { it.id == com.example.domain.repository.hero.model.HeroModel.id && it.name == com.example.domain.repository.hero.model.HeroModel.name && it.rarity == com.example.domain.repository.hero.model.HeroModel.rarity && it.idPath == com.example.domain.repository.hero.model.HeroModel.idPath && it.idElement == com.example.domain.repository.hero.model.HeroModel.idElement }
                    }

                    val localAvatarPaths =
                        downloadImages(
                            newHeroes,
                            { com.example.domain.repository.hero.model.HeroModel.avatar },
                            CHILD_HEROES_AVATARS
                        ).await()

                    val localSplashArtsPaths = downloadImages(
                        newHeroes,
                        { com.example.domain.repository.hero.model.HeroModel.splashArt },
                        CHILD_HEROES_SPLASH_ARTS
                    ).await()

                    val heroEntities = newHeroes.mapIndexed { index, hero ->
                        HeroEntity.toHeroEntity(hero).copy(
                            localAvatarPath = localAvatarPaths[index],
                            localSplashArtPath = localSplashArtsPaths[index]
                        )
                    }

                    insertEntitiesIntoLocalStorage(heroEntities, heroDao::insertHeroesList).join()

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
                    val localEntities = optimalStatsHeroDao.getOptimalStats()
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
                    val localEntities = buildWeaponDao.getBuildWeapon()
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
                    val localEntities = buildRelicDao.getBuildRelic()
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
                    val localEntities = buildDecorationDao.getBuildDecorations()
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
                    val localEntities = decorationDao.getDecorations()
                    val newEntities = remoteEntities.filter { remoteDecoration ->
                        localEntities.none { remoteDecoration == it.toDecoration() }
                    }

                    val localImageDecorations =
                        downloadImages(
                            newEntities,
                            { com.example.domain.repository.decoration.Decoration.image },
                            CHILD_DECORATIONS_IMAGE
                        ).await()

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
                    val localEntities = relicDao.getRelics()
                    val newEntities = remoteEntities.filter { remoteRelic ->
                        localEntities.none { remoteRelic == it.toRelic() }
                    }

                    val localImageRelics =
                        downloadImages(
                            newEntities,
                            { com.example.domain.repository.relic.Relic.image },
                            CHILD_RELICS_IMAGE
                        ).await()

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
                    val localEntities = buildStatsEquipmentDao.getBuildStatsEquipment()
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
                    val localEntities = weaponDao.getWeapons()
                    val newEntities = remoteEntities.filter { weapon ->
                        localEntities.none { weapon == it.toWeapon() }
                    }

                    val localImageWeapons =
                        downloadImages(
                            newEntities,
                            { com.example.domain.repository.weapon.Weapon.image },
                            CHILD_WEAPONS_IMAGE
                        ).await()

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

    private fun <T> insertEntitiesIntoLocalStorage(
        entityList: List<T>,
        insertFunction: suspend (List<T>) -> Unit
    ): Job {
        return CoroutineScope(ioDispatcher).launch {
            insertFunction(entityList)
        }
    }

    private suspend fun <T> downloadImages(
        list: List<T>, propertySelector: (T) -> String, folder: String
    ) = withContext(ioDispatcher) {
        async {
            list.map { item ->
                fileManager.saveImage(propertySelector(item), folder)
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