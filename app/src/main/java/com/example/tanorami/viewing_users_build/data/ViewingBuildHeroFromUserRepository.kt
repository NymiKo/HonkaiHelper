package com.example.tanorami.viewing_users_build.data

import com.example.tanorami.viewing_users_build.data.model.FullBuildHeroFromUser

interface ViewingBuildHeroFromUserRepository {
    suspend fun getHeroBuildByID(idBuild: Long): com.example.data.remote.NetworkResult<FullBuildHeroFromUser>
}