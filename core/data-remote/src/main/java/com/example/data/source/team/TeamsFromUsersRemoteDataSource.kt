package com.example.data.source.team

import com.example.data.remote.api.team.model.TeamFromUserDto
import com.example.domain.util.NetworkResult

interface TeamsFromUsersRemoteDataSource {
    suspend fun getTeamsFromUsersListByIdHero(idHero: Int): NetworkResult<List<TeamFromUserDto>>
    suspend fun getTeamsFromUsersList(page: Int, uid: String): List<TeamFromUserDto>
}