package com.example.tanorami.viewing_users_build.data

import com.example.core.data.source.local.hero.mapper.toHeroBaseInfoModel
import com.example.core.di.IODispatcher
import com.example.data.local.dao.DecorationDao
import com.example.data.local.dao.HeroDao
import com.example.data.local.dao.RelicDao
import com.example.data.local.dao.WeaponDao
import com.example.data.remote.NetworkResult
import com.example.data.remote.handleApi
import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ViewingBuildHeroFromUserRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val viewingBuildHeroFromUserService: ViewingBuildHeroFromUserService,
    private val heroDao: HeroDao,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao
) : ViewingBuildHeroFromUserRepository {
    override suspend fun getHeroBuildByID(idBuild: Long): NetworkResult<FullBuildHeroFromUser> =
        withContext(ioDispatcher) {
            when (val result =
                handleApi {
                    viewingBuildHeroFromUserService.getHeroBuildByID(
                        idBuild
                    )
                }) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    return@withContext NetworkResult.Success(
                        FullBuildHeroFromUser(
                            idBuild = idBuild,
                            hero = heroDao.getHeroWithNameAvatarRarity(result.data.idHero)
                                .toHeroBaseInfoModel(),
                            weapon = weaponDao.getWeapon(result.data.idWeapon)
                                .toWeapon(),
                            relicTwoParts = relicDao.getRelic(result.data.idRelicTwoParts)
                                .toRelic(),
                            relicFourParts = relicDao.getRelic(result.data.idRelicFourParts)
                                .toRelic(),
                            decoration = decorationDao.getDecoration(result.data.idDecoration)
                                .toDecoration(),
                            statsEquipment = result.data.statsEquipment.toList(),
                            nickname = result.data.nickname,
                            uid = result.data.uid
                        )
                    )
                }
            }
        }
}