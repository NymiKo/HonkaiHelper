package com.example.domain.usecase.impl

import com.example.common.HeroBaseInfoModel
import com.example.domain.repository.hero.HeroRepository
import com.example.domain.usecase.GetHeroesListWithBaseInfoUseCase
import javax.inject.Inject

internal class GetHeroesListWithBaseInfoUseCaseImpl @Inject constructor(
    private val heroRepository: HeroRepository,
) : GetHeroesListWithBaseInfoUseCase {
    override suspend fun invoke(): List<HeroBaseInfoModel> {
        return heroRepository.getHeroesListWithBaseInfo()
    }
}