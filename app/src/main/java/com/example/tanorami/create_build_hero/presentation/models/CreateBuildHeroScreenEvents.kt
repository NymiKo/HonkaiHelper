package com.example.tanorami.create_build_hero.presentation.models

import com.example.tanorami.base.UiEvent
import com.example.tanorami.create_build_hero.data.model.Equipment

sealed interface CreateBuildHeroScreenEvents : UiEvent {
    data object OnBack: CreateBuildHeroScreenEvents
    data object DeleteBuild: CreateBuildHeroScreenEvents
    data object SaveBuild: CreateBuildHeroScreenEvents
    data object UpdateBuild: CreateBuildHeroScreenEvents
    class ChangeStateEquipmentBottomSheet(val sheetState: Boolean, val equipmentType: EquipmentType = EquipmentType.WEAPON): CreateBuildHeroScreenEvents
    class GetBuild(val idBuild: Long): CreateBuildHeroScreenEvents
    class GetHero(val idHero: Int): CreateBuildHeroScreenEvents
    class AddWeapon(val weapon: Equipment): CreateBuildHeroScreenEvents
    class AddTwoPartsRelic(val twoPartsRelic: Equipment): CreateBuildHeroScreenEvents
    class AddFourPartsRelic(val fourPartsRelic: Equipment): CreateBuildHeroScreenEvents
    class AddDecoration(val decoration: Equipment): CreateBuildHeroScreenEvents
    class ChangeStatsOnBody(val value: String): CreateBuildHeroScreenEvents
    class ChangeStatsOnLegs(val value: String): CreateBuildHeroScreenEvents
    class ChangeStatsOnSphere(val value: String): CreateBuildHeroScreenEvents
    class ChangeStatsOnRope(val value: String): CreateBuildHeroScreenEvents
}