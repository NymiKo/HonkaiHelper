package com.example.honkaihelper.create_build_hero.data

import com.example.honkaihelper.create_build_hero.data.model.BuildHeroFromUser
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.local.dao.DecorationDao
import com.example.honkaihelper.data.local.dao.HeroDao
import com.example.honkaihelper.data.local.dao.RelicDao
import com.example.honkaihelper.data.local.dao.WeaponDao
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.viewing_users_build.data.model.FullBuildHeroFromUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateBuildHeroRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val heroDao: HeroDao,
    private val createBuildHeroService: CreateBuildHeroService,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao
): CreateBuildHeroRepository {
    override suspend fun getHero(idHero: Int): Hero = withContext(ioDispatcher) {
        return@withContext heroDao.getHero(idHero).toHero()
    }

    override suspend fun saveBuild(buildHeroFromUser: BuildHeroFromUser): NetworkResult<Boolean> = withContext(ioDispatcher) {
        if (buildHeroFromUser.idBuild == -1) return@withContext handleApi { createBuildHeroService.saveBuild(buildHeroFromUser) }
        else return@withContext handleApi { createBuildHeroService.updateBuild(buildHeroFromUser) }
    }

    override suspend fun getBuild(idBuild: Int): NetworkResult<FullBuildHeroFromUser> = withContext(ioDispatcher) {
        when(val result = handleApi { createBuildHeroService.getBuild(idBuild) }) {
            is NetworkResult.Error -> {
                return@withContext NetworkResult.Error(result.code)
            }
            is NetworkResult.Success -> {
                return@withContext NetworkResult.Success(
                    FullBuildHeroFromUser(
                        idBuild = idBuild,
                        hero = heroDao.getHeroWithNameAvatarRarity(result.data.idHero),
                        weapon = weaponDao.getWeapon(result.data.idWeapon).toWeapon(),
                        relicTwoParts = relicDao.getRelic(result.data.idRelicTwoParts).toRelic(),
                        relicFourParts = relicDao.getRelic(result.data.idRelicFourParts).toRelic(),
                        decoration = decorationDao.getDecoration(result.data.idDecoration).toDecoration(),
                        statsEquipment = result.data.statsEquipment.toList(),
                        nickname = result.data.nickname
                    )
                )
            }
        }
    }
}