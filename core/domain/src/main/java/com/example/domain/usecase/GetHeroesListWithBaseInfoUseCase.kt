package com.example.domain.usecase

import com.example.domain.repository.hero.model.HeroBaseInfoModel

interface GetHeroesListWithBaseInfoUseCase {
    suspend operator fun invoke(): List<HeroBaseInfoModel>
}