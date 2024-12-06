package com.example.data.source.hero

import com.example.data.remote.api.hero.model.HeroDto
import com.example.domain.util.NetworkResult

interface HeroRemoteDataSource {
    suspend fun getHeroesList(): NetworkResult<List<HeroDto>>
}