package com.example.tanorami.create_build_hero.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.create_build_hero.data.CreateBuildHeroRepository
import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
import com.example.tanorami.create_build_hero.data.model.BuildHeroModel
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.equipment.data.model.Equipment
import com.example.tanorami.heroes.data.model.Hero
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateBuildHeroViewModel @Inject constructor(
    private val repository: CreateBuildHeroRepository
) : ViewModel() {

    var uiState by mutableStateOf(CreateBuildHeroScreenUiState())

    private val _state = MutableLiveData<CreateBuildHeroUiState>(CreateBuildHeroUiState.CREATION)
    val state: LiveData<CreateBuildHeroUiState> = _state

    private val _hero = MutableLiveData<Hero>()
    val hero: LiveData<Hero> = _hero

    private val _weapon = MutableLiveData<Equipment>()
    val weapon: LiveData<Equipment> = _weapon

    private val _relicTwoParts = MutableLiveData<Equipment>()
    val relicTwoParts: LiveData<Equipment> = _relicTwoParts

    private val _relicFourParts = MutableLiveData<Equipment?>()
    val relicFourParts: LiveData<Equipment?> = _relicFourParts

    private val _decoration = MutableLiveData<Equipment>()
    val decoration: LiveData<Equipment> = _decoration

    private val statsEquipmentList = Array<String>(4) { "" }

    fun onEvent(event: CreateBuildHeroScreenEvents) {
        when (event) {
            is CreateBuildHeroScreenEvents.DeleteBuild -> deleteBuild(idBuild = uiState.idBuild!!)
            is CreateBuildHeroScreenEvents.SaveBuild -> saveBuild(idBuild = uiState.idBuild)
            is CreateBuildHeroScreenEvents.UpdateBuild -> saveBuild(idBuild = uiState.idBuild)
            is CreateBuildHeroScreenEvents.GetBuild -> {
                if (event.idBuild != -1L) {
                    uiState = uiState.copy(idBuild = event.idBuild, isCreateBuild = false)
                    getBuild(event.idBuild)
                } else {
                    uiState = uiState.copy(isCreateBuild = true)
                }
            }

            is CreateBuildHeroScreenEvents.GetHero -> {
                if (event.idHero != -1) getHero(event.idHero)
            }

            is CreateBuildHeroScreenEvents.AddWeapon -> addWeapon(event.weapon)
            is CreateBuildHeroScreenEvents.AddTwoPartsRelic -> addRelicTwoParts(event.twoPartsRelic)
            is CreateBuildHeroScreenEvents.AddFourPartsRelic -> addRelicFourParts(event.fourPartsRelic)
            is CreateBuildHeroScreenEvents.AddDecoration -> addDecoration(event.decoration)

            else -> Unit
        }
    }

    private fun getHero(idHero: Int) = viewModelScope.launch {
        //_hero.value = repository.getHero(idHero)

        uiState = uiState.copy(idHero = idHero, hero = repository.getHero(idHero))
    }

    private fun addWeapon(weapon: Equipment) {
        //_weapon.value = equipment

        uiState = uiState.copy(buildHeroFromUser = uiState.buildHeroFromUser.copy(weapon = weapon))
    }

    private fun addRelicTwoParts(twoPartsRelic: Equipment) {
        //_relicTwoParts.value = equipment

        uiState =
            uiState.copy(buildHeroFromUser = uiState.buildHeroFromUser.copy(relicTwoParts = twoPartsRelic))
    }

    private fun addRelicFourParts(fourPartsRelic: Equipment) {
        //_relicFourParts.value = equipment

        uiState =
            uiState.copy(buildHeroFromUser = uiState.buildHeroFromUser.copy(relicFourParts = fourPartsRelic))
    }

    private fun addDecoration(decoration: Equipment) {
        //_decoration.value = equipment

        uiState =
            uiState.copy(buildHeroFromUser = uiState.buildHeroFromUser.copy(decoration = decoration))
    }

    fun changeStatOnEquipment(positionItem: Int, value: String) {
        statsEquipmentList[positionItem] = value
    }

    fun saveBuild(idBuild: Long?) {
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
            //_state.value = CreateBuildHeroUiState.SENDING_BUILD

            val build = BuildHeroFromUser(
                uiState.idHero!!,
                uiState.buildHeroFromUser.weapon!!.id,
                uiState.buildHeroFromUser.relicTwoParts!!.id,
                uiState.buildHeroFromUser.relicFourParts?.id
                    ?: uiState.buildHeroFromUser.relicTwoParts!!.id,
                uiState.buildHeroFromUser.decoration!!.id,
                uiState.buildHeroFromUser.statsEquipmentList,
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
                    //_state.value = CreateBuildHeroUiState.SUCCESS
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
                //_state.value = CreateBuildHeroUiState.CREATION

                uiState = uiState.copy(
                    isLoading = false,
                    isSuccess = true,
                    isError = false,
                    hero = repository.getHero(result.data.hero.id),
                    buildHeroFromUser = BuildHeroModel(
                        idBuild = idBuild,
                        weapon = Equipment(
                            result.data.weapon.idWeapon,
                            result.data.weapon.image,
                            result.data.weapon.rarity
                        ),
                        relicTwoParts = Equipment(
                            result.data.relicTwoParts.idRelic,
                            result.data.relicTwoParts.image
                        ),
                        relicFourParts = Equipment(
                            result.data.relicFourParts.idRelic,
                            result.data.relicFourParts.image
                        ),
                        decoration = Equipment(
                            result.data.decoration.idDecoration,
                            result.data.decoration.image
                        )
                    ),
                )
            }
        }
    }

    fun deleteBuild(idBuild: Long) = viewModelScope.launch {
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
                //_state.value = CreateBuildHeroUiState.SUCCESS_DELETION_BUILD

                uiState = uiState.copy(
                    isLoading = false,
                    isError = false,
                    isSuccess = true
                )
            }
        }
    }
}