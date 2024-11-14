package com.example.tanorami.create_build_hero.presentation.models

import com.example.core.base.UiEvent
import com.example.core.domain.repository.equipment.Equipment

sealed interface CreateBuildHeroScreenEvents : UiEvent {
    data object OnBack: CreateBuildHeroScreenEvents
    data object DeleteBuild: CreateBuildHeroScreenEvents
    data object SaveBuild: CreateBuildHeroScreenEvents
    class ChangeStateEquipmentBottomSheet(
        val sheetVisibilityState: Boolean,
        val equipmentType: EquipmentType = EquipmentType.WEAPON
    ) : CreateBuildHeroScreenEvents
    class GetBuild(val idBuild: Long?, val idHero: Int?) : CreateBuildHeroScreenEvents
    class AddWeapon(val weapon: Equipment): CreateBuildHeroScreenEvents
    class AddTwoPartsRelic(val twoPartsRelic: Equipment): CreateBuildHeroScreenEvents
    class AddFourPartsRelic(val fourPartsRelic: Equipment): CreateBuildHeroScreenEvents
    class AddDecoration(val decoration: Equipment): CreateBuildHeroScreenEvents
    class ChangeStatsOnBody(val value: String): CreateBuildHeroScreenEvents
    class ChangeStatsOnLegs(val value: String): CreateBuildHeroScreenEvents
    class ChangeStatsOnSphere(val value: String): CreateBuildHeroScreenEvents
    class ChangeStatsOnRope(val value: String): CreateBuildHeroScreenEvents
    class ChangeVisibilityDialogSaveBuild(val visibility: Boolean) : CreateBuildHeroScreenEvents
    class ChangeVisibilityDialogDeleteBuild(val visibility: Boolean) : CreateBuildHeroScreenEvents
}