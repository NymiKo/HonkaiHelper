package com.example.domain.usecase.hero

import com.example.common.HeroBaseInfoModel

interface GetHeroesListWithBaseInfoUseCase {
    suspend operator fun invoke(): List<HeroBaseInfoModel>
}