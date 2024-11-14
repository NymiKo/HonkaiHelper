package com.example.core.domain.usecase

import com.example.core.domain.repository.hero.model.HeroBaseInfoModel

interface GetHeroesListWithBaseInfoUseCase {
    suspend operator fun invoke(): List<HeroBaseInfoModel>
}