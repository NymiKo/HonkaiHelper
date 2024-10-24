package com.example.tanorami.builds_hero_from_users.data

import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.handleApi
import com.example.tanorami.data.local.dao.DecorationDao
import com.example.tanorami.data.local.dao.HeroDao
import com.example.tanorami.data.local.dao.RelicDao
import com.example.tanorami.data.local.dao.WeaponDao
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BuildsHeroListRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
    private val buildsHeroListService: BuildsHeroListService,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao
): BuildsHeroListRepository {
    override suspend fun getHero(idHero: Int): HeroWithNameAvatarRarity = withContext(ioDispatcher) {
        return@withContext heroDao.getHeroWithNameAvatarRarity(idHero)
    }

    override suspend fun getBuildsHeroListByIdHero(idHero: Int): NetworkResult<List<BuildHeroWithUser>> =
        withContext(ioDispatcher) {
            when (val result =
                handleApi { buildsHeroListService.getBuildsHeroListByIdHero(idHero) }) {
            is NetworkResult.Error -> {
                return@withContext NetworkResult.Error(result.code)
            }
            is NetworkResult.Success -> {
                val hero = getHero(idHero)
                return@withContext NetworkResult.Success(result.data.map {
                    BuildHeroWithUser(
                        idBuild = it.idBuild,
                        hero = hero,
                        weapon = weaponDao.getWeapon(it.idWeapon).toWeapon(),
                        relicTwoParts = relicDao.getRelic(it.idRelicTwoParts).toRelic(),
                        relicFourParts = relicDao.getRelic(it.idRelicFourParts).toRelic(),
                        decoration = decorationDao.getDecoration(it.idDecoration).toDecoration(),
                        buildUser = it.buildUser!!
                    )
                })
            }
            }
        }

    override suspend fun getBuildsHeroList(): NetworkResult<List<BuildHeroWithUser>> =
        withContext(ioDispatcher) {
            when (val result = handleApi { buildsHeroListService.getBuildsHeroList() }) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    return@withContext NetworkResult.Success(result.data.map {
                        BuildHeroWithUser(
                            idBuild = it.idBuild,
                            hero = getHero(it.idHero),
                            weapon = weaponDao.getWeapon(it.idWeapon).toWeapon(),
                            relicTwoParts = relicDao.getRelic(it.idRelicTwoParts).toRelic(),
                            relicFourParts = relicDao.getRelic(it.idRelicFourParts).toRelic(),
                            decoration = decorationDao.getDecoration(it.idDecoration)
                                .toDecoration(),
                            buildUser = it.buildUser!!
                    )
                })
            }
        }
    }
}