package com.example.tanorami.create_build_hero.presentation

import com.example.tanorami.equipment.EquipmentType
import com.example.tanorami.equipment.data.model.Equipment

sealed interface CreateBuildHeroScreenEvents {
    data object OnBack: CreateBuildHeroScreenEvents
    data object DeleteBuild: CreateBuildHeroScreenEvents
    data object SaveBuild: CreateBuildHeroScreenEvents
    data object UpdateBuild: CreateBuildHeroScreenEvents
    data class OnEquipmentScreen(val pathHero: Int, val equipmentType: EquipmentType): CreateBuildHeroScreenEvents
    data class GetBuild(val idBuild: Long): CreateBuildHeroScreenEvents
    data class GetHero(val idHero: Int): CreateBuildHeroScreenEvents
    data class AddWeapon(val weapon: Equipment): CreateBuildHeroScreenEvents
    data class AddTwoPartsRelic(val twoPartsRelic: Equipment): CreateBuildHeroScreenEvents
    data class AddFourPartsRelic(val fourPartsRelic: Equipment): CreateBuildHeroScreenEvents
    data class AddDecoration(val decoration: Equipment): CreateBuildHeroScreenEvents
    data class ChangeStatsOnBody(val value: String): CreateBuildHeroScreenEvents
    data class ChangeStatsOnLegs(val value: String): CreateBuildHeroScreenEvents
    data class ChangeStatsOnSphere(val value: String): CreateBuildHeroScreenEvents
    data class ChangeStatsOnRope(val value: String): CreateBuildHeroScreenEvents
    data object HideToast: CreateBuildHeroScreenEvents
}