package com.example.tanorami.create_build_hero.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.create_build_hero.data.CreateBuildHeroRepository
import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenEvents
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenSideEffects
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenUiState
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.create_build_hero.data.model.Equipment
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
            is CreateBuildHeroScreenEvents.UpdateBuild -> saveBuild(idBuild = uiState.idBuild)
            is CreateBuildHeroScreenEvents.GetBuild -> {
                if (event.idBuild != -1L) {
                    uiState = uiState.copy(idBuild = event.idBuild, isCreateBuildMode = false)
                    getBuild(event.idBuild)
                } else {
                    uiState = uiState.copy(isCreateBuildMode = true)
                }
            }

            is CreateBuildHeroScreenEvents.GetHero -> getHero(event.idHero)

            is CreateBuildHeroScreenEvents.AddWeapon -> addWeapon(event.weapon)
            is CreateBuildHeroScreenEvents.AddTwoPartsRelic -> addRelicTwoParts(event.twoPartsRelic)
            is CreateBuildHeroScreenEvents.AddFourPartsRelic -> addRelicFourParts(event.fourPartsRelic)
            is CreateBuildHeroScreenEvents.AddDecoration -> addDecoration(event.decoration)

            is CreateBuildHeroScreenEvents.ChangeStatsOnBody -> changeStatBody(event.value)
            is CreateBuildHeroScreenEvents.ChangeStatsOnLegs -> changeStatLegs(event.value)
            is CreateBuildHeroScreenEvents.ChangeStatsOnSphere -> changeStatSphere(event.value)
            is CreateBuildHeroScreenEvents.ChangeStatsOnRope -> changeStatRope(event.value)
            CreateBuildHeroScreenEvents.OnBack -> sendSideEffect(CreateBuildHeroScreenSideEffects.OnBack)
            is CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet -> getEquipmentList(event.sheetState, event.equipmentType)
        }
    }

    private fun getHero(idHero: Int) = viewModelScope.launch {
        if (idHero != -1) {
            uiState = uiState.copy(idHero = idHero, hero = repository.getHero(idHero))
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
                        isSuccess = false,
                        isError = true,
                        errorMessage = errorHandler(result.code),
                    )
                }

                is NetworkResult.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isSuccess = true,
                        isError = false,
                    )
                }
            }
        }
    }

    private fun checkForNull(arg: Equipment?, message: Int): Boolean {
        return if (arg == null) {
            uiState = uiState.copy(
                isLoading = false,
                isSuccess = false,
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
                    isSuccess = false,
                    isError = true,
                    errorMessage = errorHandler(result.code)
                )
            }

            is NetworkResult.Success -> {
                uiState = uiState.copy(
                    isLoading = false,
                    isSuccess = true,
                    isError = false,
                    hero = repository.getHero(result.data.hero!!.id),
                    buildHeroFromUser = uiState.buildHeroFromUser.copy(
                        idBuild = idBuild,
                        weapon = Equipment(
                            result.data.weapon!!.idWeapon,
                            result.data.weapon.image,
                            result.data.weapon.rarity
                        ),
                        relicTwoParts = Equipment(
                            result.data.relicTwoParts!!.idRelic,
                            result.data.relicTwoParts.image
                        ),
                        relicFourParts = Equipment(
                            result.data.relicFourParts!!.idRelic,
                            result.data.relicFourParts.image
                        ),
                        decoration = Equipment(
                            result.data.decoration!!.idDecoration,
                            result.data.decoration.image
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
                    isSuccess = false,
                    isError = true,
                    errorMessage = errorHandler(result.code)
                )
            }

            is NetworkResult.Success -> {
                uiState = uiState.copy(
                    isLoading = false,
                    isError = false,
                    isSuccess = true
                )
            }
        }
    }

    private fun getEquipmentList(sheetState: Boolean, equipmentType: EquipmentType) = viewModelScope.launch {
        uiState = uiState.copy(showBottomSheet = sheetState)
        if (sheetState) {
            uiState = when(equipmentType) {
                EquipmentType.WEAPON -> uiState.copy(equipmentList = repository.getWeapons(uiState.hero?.path ?: 0), equipmentType = equipmentType)
                EquipmentType.RELIC_TWO_PARTS -> uiState.copy(equipmentList = repository.getRelics(), equipmentType = equipmentType)
                EquipmentType.RELIC_FOUR_PARTS -> uiState.copy(equipmentList = repository.getRelics(), equipmentType = equipmentType)
                EquipmentType.DECORATION -> uiState.copy(equipmentList = repository.getDecorations(), equipmentType = equipmentType)
            }
        }
    }
}