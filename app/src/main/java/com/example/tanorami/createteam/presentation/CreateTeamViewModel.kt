package com.example.tanorami.createteam.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.createteam.data.CreateTeamRepository
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateTeamViewModel @Inject constructor(
    private val repository: CreateTeamRepository
) : ViewModel() {

    var uiState by mutableStateOf(CreateTeamScreenUiState())

    private val _state = MutableLiveData<CreateTeamUIState>(CreateTeamUIState.CREATING_TEAM)
    val state: LiveData<CreateTeamUIState> = _state

    private val _heroList = MutableLiveData<List<ActiveHeroInTeam>>()
    val heroList: LiveData<List<ActiveHeroInTeam>> = _heroList

    private val _heroListInTeam = MutableLiveData<List<HeroWithNameAvatarRarity>>()
    val heroListInTeam: LiveData<List<HeroWithNameAvatarRarity>> = _heroListInTeam

    private val _selectedHero = MutableLiveData<ActiveHeroInTeam>()
    val selectedHero: LiveData<ActiveHeroInTeam> = _selectedHero

    private val _uidTeam = MutableLiveData<String>()
    val uidTeam: LiveData<String> = _uidTeam

    init {
        getHeroesList()
    }

    private fun getHeroesList() = viewModelScope.launch {
        uiState = uiState.copy(heroesList = repository.getHeroesList())
        //_heroList.value = repository.getHeroesList()
    }

    fun addHeroInTeam(activeHeroInTeam: ActiveHeroInTeam) {
        if (_heroListInTeam.value?.size != 4) {
            val currentList = _heroListInTeam.value ?: emptyList()
            val newList = currentList.toMutableList()
            newList.add(activeHeroInTeam.hero)
            _heroListInTeam.value = newList
            selectHero(activeHeroInTeam, true)
        }
    }

    fun removeHeroFromTeam(activeHeroInTeam: ActiveHeroInTeam) {
        if (_heroListInTeam.value?.size != 0) {
            _heroListInTeam.value = _heroListInTeam.value?.minus(activeHeroInTeam.hero)
            selectHero(activeHeroInTeam, false)
        }
    }

    private fun selectHero(activeHeroInTeam: ActiveHeroInTeam, active: Boolean) {
        activeHeroInTeam.active = active
        _selectedHero.value = activeHeroInTeam
    }

    fun saveTeam(idTeam: Long) = viewModelScope.launch {
        _state.value = CreateTeamUIState.LOADING_TEAM_CREATION
        when(val result = repository.saveTeam(idTeam, _heroListInTeam.value!!)) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(isSuccess = false, isError = true, message = errorHandler(result.code))
            }
            is NetworkResult.Success -> {
                uiState = uiState.copy(isSuccess = true, isError = false)
                _state.value = CreateTeamUIState.SUCCESS_TEAM_CREATION
            }
        }
    }

    private fun errorHandler(errorCode: Int): Int {
        return when (errorCode) {
            105 -> R.string.check_your_internet_connection
            400 -> R.string.team_already_exists
            503 -> R.string.error_save_in_server
            else -> R.string.error
        }
    }

    fun getTeam(idTeam: Long) = viewModelScope.launch {
        uiState = uiState.copy(isCreateTeamMode = false)
        when(val result = repository.getTeam(idTeam)) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(isSuccess = false, isError = true, message = errorHandler(result.code))
            }
            is NetworkResult.Success -> {
                result.data.second.forEach {
                    val list = _heroList.value ?: emptyList()
                    val newList = list.toMutableList()
                    newList[newList.indexOf(ActiveHeroInTeam(it))] = ActiveHeroInTeam(it, true)
                    uiState = uiState.copy(heroesList = newList)
                    //_heroList.value = newList
                    addHeroInTeam(ActiveHeroInTeam(it))
                }
                uiState = uiState.copy(
                    isSuccess = true,
                    isError = false,
                    uidTeam = result.data.first,
                    heroesListInTeam = result.data.second,
                )
//                _uidTeam.value = result.data.first
//                _state.value = CreateTeamUIState.CREATING_TEAM
            }
        }
    }

    fun deleteTeam(idTeam: Long) = viewModelScope.launch {
        when(val result = repository.deleteTeam(idTeam)) {
            is NetworkResult.Error -> errorHandler(result.code)
            is NetworkResult.Success -> {
                _state.value = CreateTeamUIState.SUCCESS_TEAM_DELETION
            }
        }
    }
}