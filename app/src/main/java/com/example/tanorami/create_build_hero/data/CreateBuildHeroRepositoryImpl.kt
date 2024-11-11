package com.example.tanorami.create_build_hero.data

import com.example.tanorami.core.database.dao.DecorationDao
import com.example.tanorami.core.database.dao.HeroDao
import com.example.tanorami.core.database.dao.RelicDao
import com.example.tanorami.core.database.dao.WeaponDao
import com.example.tanorami.core.di.IODispatcher
import com.example.tanorami.core.network.NetworkResult
import com.example.tanorami.core.network.handleApi
import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
import com.example.tanorami.create_build_hero.data.model.Equipment
import com.example.tanorami.heroes.data.model.Hero
import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateBuildHeroRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
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
        if (buildHeroFromUser.idBuild == -1L) return@withContext handleApi { createBuildHeroService.saveBuild(buildHeroFromUser) }
        else return@withContext handleApi { createBuildHeroService.updateBuild(buildHeroFromUser) }
    }

    override suspend fun getBuild(idBuild: Long): NetworkResult<FullBuildHeroFromUser> = withContext(ioDispatcher) {
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
                        nickname = result.data.nickname,
                        uid = result.data.uid
                    )
                )
            }
        }
    }

    override suspend fun deleteBuild(idBuild: Long): NetworkResult<Boolean> = withContext(ioDispatcher) {
        return@withContext handleApi { createBuildHeroService.deleteBuild(idBuild) }
    }

    override suspend fun getWeapons(path: Int): List<Equipment> = withContext(ioDispatcher) {
        weaponDao.getWeaponByPath(path).sortedByDescending { it.rarity }.map {
            Equipment(it.idWeapon, it.image, it.rarity)
        }
    }

    override suspend fun getRelics(): List<Equipment> = withContext(ioDispatcher) {
        relicDao.getRelics().map {
            Equipment(it.idRelic, it.image)
        }
    }

    override suspend fun getDecorations(): List<Equipment> = withContext(ioDispatcher) {
        decorationDao.getDecorations().map {
            Equipment(it.idDecoration, it.image)
        }
    }
}