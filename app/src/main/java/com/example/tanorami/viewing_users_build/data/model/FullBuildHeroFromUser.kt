package com.example.tanorami.viewing_users_build.data.model

import com.example.common.DecorationModel
import com.example.common.HeroBaseInfoModel
import com.example.common.RelicModel
import com.example.common.WeaponModel

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
