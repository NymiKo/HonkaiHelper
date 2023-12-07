package com.example.tanorami.create_build_hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.create_build_hero.data.CreateBuildHeroRepository
import com.example.tanorami.create_build_hero.data.model.BuildHeroFromUser
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.equipment.data.model.Equipment
import com.example.tanorami.heroes.data.model.Hero
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateBuildHeroViewModel @Inject constructor(
    private val repository: CreateBuildHeroRepository
) : ViewModel() {

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

    fun getHero(idHero: Int) = viewModelScope.launch {
        _hero.value = repository.getHero(idHero)
    }

    fun addWeapon(equipment: Equipment) {
        _weapon.value = equipment
    }

    fun addRelicTwoParts(equipment: Equipment) {
        _relicTwoParts.value = equipment
    }

    fun addRelicFourParts(equipment: Equipment) {
        _relicFourParts.value = equipment
    }

    fun addDecoration(equipment: Equipment) {
        _decoration.value = equipment
    }

    fun changeStatOnEquipment(positionItem: Int, value: String) {
        statsEquipmentList[positionItem] = value
    }

    fun saveBuild(idBuild: Int?) {
        if (!checkForNull(_weapon.value, R.string.empty_weapon_in_create_build)) {
            return
        }

        if (!checkForNull(_relicTwoParts.value, R.string.empty_relic_in_create_build)) {
            return
        }

        if (!checkForNull(_decoration.value, R.string.empty_decoration_in_create_build)) {
            return
        }

        viewModelScope.launch {
            _state.value = CreateBuildHeroUiState.SENDING_BUILD

            val build = BuildHeroFromUser(
                _hero.value!!.id,
                _weapon.value!!.id,
                _relicTwoParts.value!!.id,
                _relicFourParts.value?.id ?: _relicTwoParts.value!!.id ,
                _decoration.value!!.id,
                statsEquipmentList,
                idBuild = idBuild
            )
            when (val result = repository.saveBuild(build)) {
                is NetworkResult.Error -> {
                    errorHandler(result.code)
                }

                is NetworkResult.Success -> {
                    _state.value = CreateBuildHeroUiState.SUCCESS
                }
            }
        }
    }

    private fun checkForNull(arg: Equipment?, message: Int): Boolean {
        return if (arg == null) {
            _state.value = CreateBuildHeroUiState.ERROR(message)
            false
        } else {
            true
        }
    }

    private fun errorHandler(errorCode: Int) {
        when (errorCode) {
            105 -> _state.value =
                CreateBuildHeroUiState.ERROR(R.string.check_your_internet_connection)
            400 -> _state.value = CreateBuildHeroUiState.ERROR(R.string.already_shared_the_build)
            503 -> _state.value = CreateBuildHeroUiState.ERROR(R.string.error_save_in_server)
            else -> _state.value = CreateBuildHeroUiState.ERROR(R.string.error)
        }
    }

    fun getBuild(idBuild: Int) = viewModelScope.launch {
        when (val result = repository.getBuild(idBuild)) {
            is NetworkResult.Error -> {
                errorHandler(result.code)
            }

            is NetworkResult.Success -> {
                _state.value = CreateBuildHeroUiState.CREATION
                _weapon.value = Equipment(result.data.weapon.idWeapon, result.data.weapon.image, result.data.weapon.rarity.toByte())
                _relicTwoParts.value = Equipment(result.data.relicTwoParts.idRelic, result.data.relicTwoParts.image)
                _relicFourParts.value = Equipment(result.data.relicFourParts.idRelic, result.data.relicFourParts.image)
                _decoration.value = Equipment(result.data.decoration.idDecoration, result.data.decoration.image)
                _hero.value = repository.getHero(result.data.hero.id)
            }
        }
    }

    fun deleteBuild(idBuild: Int) = viewModelScope.launch {
        when (val result = repository.deleteBuild(idBuild)) {
            is NetworkResult.Error -> {
                errorHandler(result.code)
            }

            is NetworkResult.Success -> {
                _state.value = CreateBuildHeroUiState.SUCCESS_DELETION_BUILD
            }
        }
    }
}