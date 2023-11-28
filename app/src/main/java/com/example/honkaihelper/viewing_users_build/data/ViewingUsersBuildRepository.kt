package com.example.honkaihelper.viewing_users_build.data

import com.example.honkaihelper.create_build_hero.data.model.BuildHeroFromUser
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.viewing_users_build.data.model.FullBuildHeroFromUser

interface ViewingUsersBuildRepository {
    suspend fun getHeroBuild(idBuild: Int): NetworkResult<FullBuildHeroFromUser>
}