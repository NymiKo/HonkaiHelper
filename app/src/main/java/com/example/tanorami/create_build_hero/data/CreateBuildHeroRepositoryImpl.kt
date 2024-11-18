package com.example.tanorami.create_build_hero.data

import com.example.core.data.source.local.hero.mapper.toHeroBaseInfoModel
import com.example.core.di.IODispatcher
import com.example.data.local.dao.DecorationDao
import com.example.data.local.dao.HeroDao
import com.example.data.local.dao.RelicDao
import com.example.data.local.dao.WeaponDao
import com.example.data.remote.NetworkResult
import com.example.data.remote.handleApi
import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
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
) : CreateBuildHeroRepository {
    override suspend fun getHero(idHero: Int): com.example.domain.repository.hero.model.HeroModel =
        withContext(ioDispatcher) {
            return@withContext heroDao.getHeroById(idHero).toHeroModel()
        }

    override suspend fun saveBuild(buildHeroFromUser: BuildHeroFromUser): NetworkResult<Boolean> =
        withContext(ioDispatcher) {
            if (buildHeroFromUser.idBuild == -1L) return@withContext handleApi {
                createBuildHeroService.saveBuild(
                    buildHeroFromUser
                )
            }
            else return@withContext handleApi {
                createBuildHeroService.updateBuild(
                    buildHeroFromUser
                )
            }
        }

    override suspend fun getBuild(idBuild: Long): NetworkResult<FullBuildHeroFromUser> =
        withContext(ioDispatcher) {
            when (val result = handleApi { createBuildHeroService.getBuild(idBuild) }) {
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

    override suspend fun deleteBuild(idBuild: Long): NetworkResult<Boolean> =
        withContext(ioDispatcher) {
            return@withContext handleApi {
                createBuildHeroService.deleteBuild(
                    idBuild
                )
            }
        }

    override suspend fun getWeapons(path: Int): List<com.example.domain.repository.equipment.Equipment> =
        withContext(ioDispatcher) {
            weaponDao.getWeaponByPath(path).sortedByDescending { it.rarity }.map {
                com.example.domain.repository.equipment.Equipment(it.idWeapon, it.image, it.rarity)
            }
        }

    override suspend fun getRelics(): List<com.example.domain.repository.equipment.Equipment> =
        withContext(ioDispatcher) {
            relicDao.getRelics().map {
                com.example.domain.repository.equipment.Equipment(it.idRelic, it.image)
            }
        }

    override suspend fun getDecorations(): List<com.example.domain.repository.equipment.Equipment> =
        withContext(ioDispatcher) {
            decorationDao.getDecorations().map {
                com.example.domain.repository.equipment.Equipment(it.idDecoration, it.image)
            }
        }
}