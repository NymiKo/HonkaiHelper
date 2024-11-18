package com.example.tanorami.createteam.data.model

import com.example.domain.repository.hero.model.HeroBaseInfoModel

data class ActiveHeroInTeam(val hero: HeroBaseInfoModel, var active: Boolean = false)
