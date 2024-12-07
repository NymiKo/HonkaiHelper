package com.example.tanorami.createteam.data.model

import com.example.common.HeroBaseInfoModel

data class ActiveHeroInTeam(
    val hero: HeroBaseInfoModel,
    var active: Boolean = false,
)
