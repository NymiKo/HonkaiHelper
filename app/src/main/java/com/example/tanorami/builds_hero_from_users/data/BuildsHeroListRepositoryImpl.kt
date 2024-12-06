package com.example.tanorami.builds_hero_from_users.data

import com.example.common.HeroBuildModel
import com.example.data.db.dao.DecorationDao
import com.example.data.db.dao.HeroDao
import com.example.data.db.dao.RelicDao
import com.example.data.db.dao.WeaponDao
import com.example.data.remote.util.handleApi
import com.example.data.source.hero.mapper.toHeroBaseInfoModel
import com.example.domain.di.DispatcherIo
import com.example.domain.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BuildsHeroListRepositoryImpl @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
    private val buildsHeroListService: BuildsHeroListService,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao,
) : BuildsHeroListRepository {
    override suspend fun getHero(idHero: Int): com.example.common.HeroBaseInfoModel =
        withContext(ioDispatcher) {
        return@withContext heroDao.getHeroWithNameAvatarRarity(idHero).toHeroBaseInfoModel()
    }

    override suspend fun getBuildsHeroListByIdHero(idHero: Int): NetworkResult<List<HeroBuildModel>> =
        withContext(ioDispatcher) {
            when (val result =
                handleApi { buildsHeroListService.getBuildsHeroListByIdHero(idHero) }) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    val hero = getHero(idHero)
                    return@withContext NetworkResult.Success(result.data.map {
                        HeroBuildModel(
                            idBuild = it.idBuild,
                            hero = hero,
                            weapon = weaponDao.getWeapon(it.idWeapon).toWeapon(),
                            relicTwoParts = relicDao.getRelic(it.idRelicTwoParts).toRelic(),
                            relicFourParts = relicDao.getRelic(it.idRelicFourParts).toRelic(),
                            decoration = decorationDao.getDecoration(it.idDecoration)
                                .toDecoration(),
                            buildUser = it.buildUser
                        )
                    })
                }
            }
        }
}