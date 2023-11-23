package com.example.honkaihelper.createteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.R
import com.example.honkaihelper.create_build_hero.CreateBuildHeroUiState
import com.example.honkaihelper.createteam.data.CreateTeamRepository
import com.example.honkaihelper.createteam.data.model.ActiveHeroInTeam
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateTeamViewModel @Inject constructor(
    private val repository: CreateTeamRepository
) : ViewModel() {

    private val _state = MutableLiveData<CreateTeamUIState>(CreateTeamUIState.CREATING_TEAM)
    val state: LiveData<CreateTeamUIState> = _state

    private val _heroList = MutableLiveData<List<ActiveHeroInTeam>>()
    val heroList: LiveData<List<ActiveHeroInTeam>> = _heroList

    private val _heroListInTeam = MutableLiveData<List<Hero>>()
    val heroListInTeam: LiveData<List<Hero>> = _heroListInTeam

    private val _selectedHero = MutableLiveData<ActiveHeroInTeam>()
    val selectedHero: LiveData<ActiveHeroInTeam> = _selectedHero

    init {
        getHeroesList()
    }

    private fun getHeroesList() = viewModelScope.launch {
        _heroList.value = repository.getHeroesList()
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

    fun saveTeam() = viewModelScope.launch {
        _state.value = CreateTeamUIState.LOADING_TEAM_CREATION
        val result = repository.saveTeam(_heroListInTeam.value!!)
        when(result) {
            is NetworkResult.Error -> errorHandler(result.code)
            is NetworkResult.Success -> {
                _state.value = CreateTeamUIState.SUCCESS_TEAM_CREATION
            }
        }
    }

    private fun errorHandler(errorCode: Int) {
        when (errorCode) {
            105 -> _state.value =
                CreateTeamUIState.ERROR_TEAM_CREATION(R.string.check_your_internet_connection)
            400 -> _state.value = CreateTeamUIState.ERROR_TEAM_CREATION(R.string.team_already_exists)
            503 -> _state.value = CreateTeamUIState.ERROR_TEAM_CREATION(R.string.error_save_in_server)
            else -> _state.value = CreateTeamUIState.ERROR_TEAM_CREATION(R.string.error)
        }
    }
}