package com.example.tanorami.createteam.data

import com.example.data.db.dao.HeroDao
import com.example.data.remote.util.NetworkResult
import com.example.data.remote.util.handleApi
import com.example.data.source.hero.mapper.toHeroBaseInfoModel
import com.example.domain.di.DispatcherIo
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateTeamRepositoryImpl @Inject constructor(
    private val createTeamService: CreateTeamService,
    private val heroDao: HeroDao,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : CreateTeamRepository {

    override suspend fun getHeroesList(): List<ActiveHeroInTeam> = withContext(ioDispatcher) {
        return@withContext heroDao.getHeroesListWithNameAvatarRarity().sortedBy { it.name }
            .map { ActiveHeroInTeam(it.toHeroBaseInfoModel(), false) }
    }

    override suspend fun saveTeam(
        idTeam: Long,
        heroesList: List<HeroBaseInfoModel>
    ): NetworkResult<Unit> {
        return withContext(ioDispatcher) {
            if (idTeam != -1L) {
                handleApi {
                    createTeamService.updateTeam(idTeam, heroesList.map { hero -> hero.id })
                }
            } else {
                handleApi {
                    createTeamService.saveTeam(heroesList.map { hero -> hero.id })
                }
            }
        }
    }

    override suspend fun getTeam(idTeam: Long): NetworkResult<Pair<String, List<HeroBaseInfoModel>>> =
        withContext(ioDispatcher) {
            when (val result = handleApi { createTeamService.getTeam(idTeam) }) {
                is NetworkResult.Error -> {
                    return@withContext NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    return@withContext NetworkResult.Success(
                        Pair(
                            result.data.uid,
                            listOf<HeroBaseInfoModel>(
                                heroDao.getHeroWithNameAvatarRarity(result.data.idHeroOne)
                                    .toHeroBaseInfoModel(),
                                heroDao.getHeroWithNameAvatarRarity(result.data.idHeroTwo)
                                    .toHeroBaseInfoModel(),
                                heroDao.getHeroWithNameAvatarRarity(result.data.idHeroThree)
                                    .toHeroBaseInfoModel(),
                                heroDao.getHeroWithNameAvatarRarity(result.data.idHeroFour)
                                    .toHeroBaseInfoModel()
                            )
                        )
                    )
                }
            }
        }

    override suspend fun deleteTeam(idTeam: Long): NetworkResult<Boolean> =
        withContext(ioDispatcher) {
            return@withContext handleApi {
                createTeamService.deleteTeam(
                    idTeam
                )
            }
        }
}