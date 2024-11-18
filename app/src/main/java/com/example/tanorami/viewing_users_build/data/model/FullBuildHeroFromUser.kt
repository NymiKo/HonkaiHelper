package com.example.tanorami.viewing_users_build.data.model

import com.example.domain.repository.decoration.DecorationModel
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.domain.repository.relic.RelicModel
import com.example.domain.repository.weapon.WeaponModel

data class FullBuildHeroFromUser(
    val idBuild: Long = -1L,
    val hero: HeroBaseInfoModel? = null,
    val weapon: WeaponModel? = null,
    val relicTwoParts: RelicModel? = null,
    val relicFourParts: RelicModel? = null,
    val decoration: DecorationModel? = null,
    val statsEquipment: List<String> = List(4) { "" },
    val nickname: String = "",
    val uid: String = "",
)
