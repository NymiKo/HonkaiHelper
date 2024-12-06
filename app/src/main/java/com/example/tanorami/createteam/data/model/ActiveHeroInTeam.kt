package com.example.tanorami.createteam.data.model

data class ActiveHeroInTeam(
    val hero: com.example.common.HeroBaseInfoModel,
    var active: Boolean = false,
)
