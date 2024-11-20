package com.example.tanorami.create_build_hero.presentation.models

import com.example.base.UiEvent

sealed interface CreateBuildHeroScreenEvents : UiEvent {
    data object OnBack: CreateBuildHeroScreenEvents
    data object DeleteBuild: CreateBuildHeroScreenEvents
    data object SaveBuild: CreateBuildHeroScreenEvents
    class ChangeStateEquipmentBottomSheet(
        val sheetVisibilityState: Boolean,
        val equipmentType: EquipmentType = EquipmentType.WEAPON
    ) : CreateBuildHeroScreenEvents
    class GetBuild(val idBuild: Long?, val idHero: Int?) : CreateBuildHeroScreenEvents
    class AddWeapon(val weapon: com.example.domain.repository.equipment.Equipment) :
        CreateBuildHeroScreenEvents

    class AddTwoPartsRelic(val twoPartsRelic: com.example.domain.repository.equipment.Equipment) :
        CreateBuildHeroScreenEvents

    class AddFourPartsRelic(val fourPartsRelic: com.example.domain.repository.equipment.Equipment) :
        CreateBuildHeroScreenEvents

    class AddDecoration(val decoration: com.example.domain.repository.equipment.Equipment) :
        CreateBuildHeroScreenEvents
    class ChangeStatsOnBody(val value: String): CreateBuildHeroScreenEvents
    class ChangeStatsOnLegs(val value: String): CreateBuildHeroScreenEvents
    class ChangeStatsOnSphere(val value: String): CreateBuildHeroScreenEvents
    class ChangeStatsOnRope(val value: String): CreateBuildHeroScreenEvents
    class ChangeVisibilityDialogSaveBuild(val visibility: Boolean) : CreateBuildHeroScreenEvents
    class ChangeVisibilityDialogDeleteBuild(val visibility: Boolean) : CreateBuildHeroScreenEvents
}