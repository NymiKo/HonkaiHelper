package com.example.tanorami.viewing_users_build.data

import com.example.tanorami.data.NetworkResult
import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser

interface ViewingUsersBuildRepository {
    suspend fun getHeroBuild(idBuild: Long): NetworkResult<FullBuildHeroFromUser>
}