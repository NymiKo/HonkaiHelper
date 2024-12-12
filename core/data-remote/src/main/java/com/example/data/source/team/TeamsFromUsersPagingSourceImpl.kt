package com.example.data.source.team

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.api.team.model.TeamFromUserDto

class TeamsFromUsersPagingSourceImpl(
    private val teamsFromUsersRemoteDataSource: TeamsFromUsersRemoteDataSource,
) : PagingSource<Int, TeamFromUserDto>() {
    override fun getRefreshKey(state: PagingState<Int, TeamFromUserDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TeamFromUserDto> {
        return try {
            val page = params.key ?: 1
            val response = teamsFromUsersRemoteDataSource.getTeamsFromUsersList(page = page)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}