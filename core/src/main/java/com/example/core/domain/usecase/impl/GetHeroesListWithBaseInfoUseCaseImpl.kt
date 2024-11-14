package com.example.core.domain.usecase.impl

import com.example.core.domain.repository.hero.HeroRepository
import com.example.core.domain.repository.hero.model.HeroBaseInfoModel
import com.example.core.domain.usecase.GetHeroesListWithBaseInfoUseCase
import javax.inject.Inject

class GetHeroesListWithBaseInfoUseCaseImpl @Inject constructor(
    private val heroRepository: HeroRepository
) : GetHeroesListWithBaseInfoUseCase {
    override suspend fun invoke(): List<HeroBaseInfoModel> {
        return heroRepository.getHeroesListWithBaseInfo()
    }
}