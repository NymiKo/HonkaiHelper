package com.example.domain.usecase

import com.example.common.HeroBaseInfoModel

interface GetHeroesListWithBaseInfoUseCase {
    suspend operator fun invoke(): List<HeroBaseInfoModel>
}