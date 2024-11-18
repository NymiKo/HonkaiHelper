package com.example.tanorami.create_build_hero.presentation

import androidx.lifecycle.viewModelScope
import com.example.core.R
import com.example.core.base.BaseViewModel
import com.example.data.remote.NetworkResult
import com.example.domain.repository.equipment.Equipment
import com.example.tanorami.create_build_hero.data.CreateBuildHeroRepository
import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenEvents
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenSideEffects
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenUiState
import com.example.tanorami.create_build_hero.presentation.models.EquipmentType
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateBuildHeroViewModel @Inject constructor(
    private val repository: CreateBuildHeroRepository,
) : BaseViewModel<CreateBuildHeroScreenUiState, CreateBuildHeroScreenEvents, CreateBuildHeroScreenSideEffects>(
    initialState = CreateBuildHeroScreenUiState()
) {
    override fun onEvent(event: CreateBuildHeroScreenEvents) {
        when (event) {
            is CreateBuildHeroScreenEvents.DeleteBuild -> deleteBuild(idBuild = uiState.idBuild)
            is CreateBuildHeroScreenEvents.SaveBuild -> saveBuild(idBuild = uiState.idBuild)
            is CreateBuildHeroScreenEvents.GetBuild -> {
                val idBuild = event.idBuild ?: -1L
                val idHero = event.idHero ?: -1
                if (idBuild != -1L) {
                    uiState = uiState.copy(idBuild = idBuild, isCreateBuildMode = false)
                    getBuild(idBuild)
                } else {
                    getHero(idHero)
                    uiState = uiState.copy(isCreateBuildMode = true)
                }
            }

            is CreateBuildHeroScreenEvents.AddWeapon -> addWeapon(event.weapon)
            is CreateBuildHeroScreenEvents.AddTwoPartsRelic -> addRelicTwoParts(event.twoPartsRelic)
            is CreateBuildHeroScreenEvents.AddFourPartsRelic -> addRelicFourParts(event.fourPartsRelic)
            is CreateBuildHeroScreenEvents.AddDecoration -> addDecoration(event.decoration)

            is CreateBuildHeroScreenEvents.ChangeStatsOnBody -> changeStatBody(event.value)
            is CreateBuildHeroScreenEvents.ChangeStatsOnLegs -> changeStatLegs(event.value)
            is CreateBuildHeroScreenEvents.ChangeStatsOnSphere -> changeStatSphere(event.value)
            is CreateBuildHeroScreenEvents.ChangeStatsOnRope -> changeStatRope(event.value)
            CreateBuildHeroScreenEvents.OnBack -> sendSideEffect(CreateBuildHeroScreenSideEffects.OnBack)
            is CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet -> getEquipmentList(
                event.sheetVisibilityState,
                event.equipmentType
            )

            is CreateBuildHeroScreenEvents.ChangeVisibilityDialogSaveBuild -> uiState =
                uiState.copy(dialogSaveBuildVisibilityState = event.visibility)

            is CreateBuildHeroScreenEvents.ChangeVisibilityDialogDeleteBuild -> uiState =
                uiState.copy(dialogDeleteBuildVisibilityState = event.visibility)
        }
    }

    private fun getHero(idHero: Int) = viewModelScope.launch {
        if (idHero != -1) {
            uiState = uiState.copy(idHero = idHero, heroModel = repository.getHero(idHero))
        }
    }

    private fun addWeapon(weapon: Equipment) {
        uiState = uiState.copy(buildHeroFromUser = uiState.buildHeroFromUser.copy(weapon = weapon))
    }

    private fun addRelicTwoParts(twoPartsRelic: Equipment) {
        uiState =
            uiState.copy(buildHeroFromUser = uiState.buildHeroFromUser.copy(relicTwoParts = twoPartsRelic))
    }

    private fun addRelicFourParts(fourPartsRelic: Equipment) {
        uiState =
            uiState.copy(buildHeroFromUser = uiState.buildHeroFromUser.copy(relicFourParts = fourPartsRelic))
    }

    private fun addDecoration(decoration: Equipment) {
        uiState =
            uiState.copy(buildHeroFromUser = uiState.buildHeroFromUser.copy(decoration = decoration))
    }

    private fun changeStatBody(value: String) {
        uiState = uiState.copy(
            buildHeroFromUser = uiState.buildHeroFromUser.copy(
                statsEquipmentList = uiState.buildHeroFromUser.statsEquipmentList.copy(statBody = value)
            )
        )
    }

    private fun changeStatLegs(value: String) {
        uiState = uiState.copy(
            buildHeroFromUser = uiState.buildHeroFromUser.copy(
                statsEquipmentList = uiState.buildHeroFromUser.statsEquipmentList.copy(statLegs = value)
            )
        )
    }

    private fun changeStatSphere(value: String) {
        uiState = uiState.copy(
            buildHeroFromUser = uiState.buildHeroFromUser.copy(
                statsEquipmentList = uiState.buildHeroFromUser.statsEquipmentList.copy(statSphere = value)
            )
        )
    }

    private fun changeStatRope(value: String) {
        uiState = uiState.copy(
            buildHeroFromUser = uiState.buildHeroFromUser.copy(
                statsEquipmentList = uiState.buildHeroFromUser.statsEquipmentList.copy(statRope = value)
            )
        )
    }

    private fun saveBuild(idBuild: Long?) {
        if (!checkForNull(
                uiState.buildHeroFromUser.weapon,
                R.string.empty_weapon_in_create_build
            )
        ) {
            return
        }

        if (!checkForNull(
                uiState.buildHeroFromUser.relicTwoParts,
                R.string.empty_relic_in_create_build
            )
        ) {
            return
        }

        if (!checkForNull(
                uiState.buildHeroFromUser.decoration,
                R.string.empty_decoration_in_create_build
            )
        ) {
            return
        }

        viewModelScope.launch {
            val build = BuildHeroFromUser(
                uiState.idHero,
                uiState.buildHeroFromUser.weapon!!.id,
                uiState.buildHeroFromUser.relicTwoParts!!.id,
                uiState.buildHeroFromUser.relicFourParts?.id
                    ?: uiState.buildHeroFromUser.relicTwoParts!!.id,
                uiState.buildHeroFromUser.decoration!!.id,
                arrayOf(
                    uiState.buildHeroFromUser.statsEquipmentList.statBody,
                    uiState.buildHeroFromUser.statsEquipmentList.statLegs,
                    uiState.buildHeroFromUser.statsEquipmentList.statSphere,
                    uiState.buildHeroFromUser.statsEquipmentList.statRope,
                ),
                idBuild = idBuild
            )

            when (val result = repository.saveBuild(build)) {
                is NetworkResult.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = errorHandler(result.code),
                        dialogSaveBuildVisibilityState = false,
                    )
                }

                is NetworkResult.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isError = false,
                        dialogSaveBuildVisibilityState = false
                    )
                    if (uiState.isCreateBuildMode) {
                        sendSideEffect(CreateBuildHeroScreenSideEffects.OnMainScreen)
                    } else {
                        sendSideEffect(CreateBuildHeroScreenSideEffects.OnBack)
                    }
                }
            }
        }
    }

    private fun checkForNull(
        arg: Equipment?,
        message: Int
    ): Boolean {
        return if (arg == null) {
            uiState = uiState.copy(
                isLoading = false,
                isError = true,
                errorMessage = message
            )
            false
        } else {
            true
        }
    }

    private fun errorHandler(errorCode: Int): Int {
        return when (errorCode) {
            105 -> R.string.check_your_internet_connection
            400 -> R.string.already_shared_the_build
            503 -> R.string.error_save_in_server
            else -> R.string.error
        }
    }

    private fun getBuild(idBuild: Long) = viewModelScope.launch {
        when (val result = repository.getBuild(idBuild)) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = errorHandler(result.code)
                )
            }

            is NetworkResult.Success -> {
                uiState = uiState.copy(
                    isLoading = false,
                    isError = false,
                    heroModel = repository.getHero(result.data.hero!!.id),
                    buildHeroFromUser = uiState.buildHeroFromUser.copy(
                        idBuild = idBuild,
                        weapon = Equipment(
                            result.data.weapon!!.idWeapon,
                            result.data.weapon!!.image,
                            result.data.weapon!!.rarity
                        ),
                        relicTwoParts = Equipment(
                            result.data.relicTwoParts!!.idRelic,
                            result.data.relicTwoParts!!.image
                        ),
                        relicFourParts = Equipment(
                            result.data.relicFourParts!!.idRelic,
                            result.data.relicFourParts!!.image
                        ),
                        decoration = Equipment(
                            result.data.decoration!!.idDecoration,
                            result.data.decoration!!.image
                        ),
                        statsEquipmentList = uiState.buildHeroFromUser.statsEquipmentList.copy(
                            statBody = result.data.statsEquipment[0],
                            statLegs = result.data.statsEquipment[1],
                            statSphere = result.data.statsEquipment[2],
                            statRope = result.data.statsEquipment[3],
                        ),
                        uid = result.data.uid
                    ),
                )
            }
        }
    }

    private fun deleteBuild(idBuild: Long) = viewModelScope.launch {
        when (val result = repository.deleteBuild(idBuild)) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = errorHandler(result.code),
                    dialogDeleteBuildVisibilityState = false
                )
            }

            is NetworkResult.Success -> {
                uiState = uiState.copy(
                    isLoading = false,
                    isError = false,
                    dialogDeleteBuildVisibilityState = false,
                )
                sendSideEffect(CreateBuildHeroScreenSideEffects.OnBack)
            }
        }
    }

    private fun getEquipmentList(sheetState: Boolean, equipmentType: EquipmentType) =
        viewModelScope.launch {
            uiState = uiState.copy(bottomSheetEquipmentVisibilityState = sheetState)
            if (sheetState) {
                uiState = when (equipmentType) {
                    EquipmentType.WEAPON -> uiState.copy(
                        equipmentList = repository.getWeapons(
                            uiState.heroModel?.idPath ?: 0
                        ), equipmentType = equipmentType
                    )

                    EquipmentType.RELIC_TWO_PARTS -> uiState.copy(
                        equipmentList = repository.getRelics(),
                        equipmentType = equipmentType
                    )

                    EquipmentType.RELIC_FOUR_PARTS -> uiState.copy(
                        equipmentList = repository.getRelics(),
                        equipmentType = equipmentType
                    )

                    EquipmentType.DECORATION -> uiState.copy(
                        equipmentList = repository.getDecorations(),
                        equipmentType = equipmentType
                    )
                }
            }
        }
}