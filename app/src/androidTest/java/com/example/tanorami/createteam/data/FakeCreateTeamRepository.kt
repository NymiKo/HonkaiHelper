package com.example.tanorami.createteam.data

//class FakeCreateTeamRepository @Inject constructor(): CreateTeamRepository {
//    override suspend fun getHeroesList(): List<ActiveHeroInTeam> {
//        return emptyList()
//    }
//
//    override suspend fun saveTeam(
//        idTeam: Long,
//        heroesList: List<HeroBaseInfoModel>,
//    ): NetworkResult<Unit> {
//        return NetworkResult.Success(Unit)
//    }
//
//    override suspend fun getTeam(idTeam: Long): NetworkResult<Pair<String, List<HeroBaseInfoModel>>> {
//        return NetworkResult.Success(Pair("", emptyList()))
//    }
//
//    override suspend fun deleteTeam(idTeam: Long): NetworkResult<Boolean> {
//        return NetworkResult.Success(true)
//    }
//}