package com.example.tanorami.teams.data.model

import com.example.domain.repository.hero.model.HeroBaseInfoModel

data class TeamHeroes(
    val idTeam: Long,
    val heroOne: HeroBaseInfoModel,
    val heroTwo: HeroBaseInfoModel,
    val heroThree: HeroBaseInfoModel,
    val heroFour: HeroBaseInfoModel,
    val nickname: String? = null,
    val avatar: String? = null,
    val uid: String = ""
)
