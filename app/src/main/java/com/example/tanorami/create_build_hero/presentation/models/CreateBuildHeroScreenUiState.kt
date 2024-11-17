package com.example.tanorami.create_build_hero.presentation.models

import com.example.core.R
import com.example.core.base.UiState
import com.example.tanorami.create_build_hero.data.model.BuildHeroModel

data class CreateBuildHeroScreenUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: Int = R.string.error,
    val isCreateBuildMode: Boolean  = true,
    val buildHeroFromUser: BuildHeroModel = BuildHeroModel(),
    val idBuild: Long = -1L,
    val idHero: Int = -1,
    val heroModel: com.example.domain.repository.hero.model.HeroModel? = null,
    val bottomSheetEquipmentVisibilityState: Boolean = false,
    val equipmentType: EquipmentType = EquipmentType.WEAPON,
    val equipmentList: List<com.example.domain.repository.equipment.Equipment> = emptyList(),
    val dialogSaveBuildVisibilityState: Boolean = false,
    val dialogDeleteBuildVisibilityState: Boolean = false,
) : UiState
