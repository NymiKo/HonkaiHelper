package com.example.domain.repository.weapon.models

import com.example.common.HeroBaseInfoModel
import com.example.common.WeaponModel
import com.example.domain.repository.path.PathModel

data class WeaponWithHeroesModel(
    val weapon: WeaponModel,
    val path: PathModel,
    val heroesList: List<HeroBaseInfoModel>,
)
