package com.example.tanorami.builds_hero_from_users.data

import com.example.core.database.dao.DecorationDao
import com.example.core.database.dao.HeroDao
import com.example.core.database.dao.RelicDao
import com.example.core.database.dao.WeaponDao
import com.example.core.database.models.hero.HeroBaseInfoProjection
import com.example.core.di.IODispatcher
import com.example.core.network.NetworkResult
import com.example.core.network.handleApi
import com.example.tanorami.builds_hero_from_users.data.model.BuildHeroWithUser
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
    override suspend fun getHero(idHero: Int): HeroBaseInfoProjection = withContext(ioDispatcher) {
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
}