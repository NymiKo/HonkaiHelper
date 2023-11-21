package com.example.honkaihelper.create_build_hero

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.R
import com.example.honkaihelper.create_build_hero.data.CreateBuildHeroRepository
import com.example.honkaihelper.create_build_hero.data.model.BuildHeroFromUser
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.heroes.data.model.Hero
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

    private val _relic = MutableLiveData<Equipment>()
    val relic: LiveData<Equipment> = _relic

    private val _decoration = MutableLiveData<Equipment>()
    val decoration: LiveData<Equipment> = _decoration

    private val statsEquipmentList = Array<String>(4) { "" }

    fun getHero(idHero: Int) = viewModelScope.launch {
        _hero.value = repository.getHero(idHero)
    }

    fun addWeapon(equipment: Equipment) {
        _weapon.value = equipment
    }

    fun addRelic(equipment: Equipment) {
        _relic.value = equipment
    }

    fun addDecoration(equipment: Equipment) {
        _decoration.value = equipment
    }

    fun changeStatOnEquipment(positionItem: Int, value: String) {
        statsEquipmentList[positionItem] = value
    }

    fun saveBuild() {
        if (!checkForNull(_weapon.value, R.string.empty_weapon_in_create_build)) {
            return
        }

        if (!checkForNull(_relic.value, R.string.empty_relic_in_create_build)) {
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
                _relic.value!!.id,
                _decoration.value!!.id,
                statsEquipmentList
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
}