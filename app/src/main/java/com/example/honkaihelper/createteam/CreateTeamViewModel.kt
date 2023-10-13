package com.example.honkaihelper.createteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.R
import com.example.honkaihelper.createteam.data.CreateTeamRepository
import com.example.honkaihelper.createteam.data.model.ActiveHeroInTeam
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateTeamViewModel @Inject constructor(
    private val repository: CreateTeamRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<CreateTeamUIState<Any>>(CreateTeamUIState.LOADING)
    val uiState: LiveData<CreateTeamUIState<Any>> = _uiState

    private val _heroListInTeam = MutableLiveData<List<Hero>>()
    val heroListInTeam: LiveData<List<Hero>> = _heroListInTeam

    private val _selectedHero = MutableLiveData<ActiveHeroInTeam>()
    val selectedHero: LiveData<ActiveHeroInTeam> = _selectedHero

    init {
        getHeroesList()
    }

    fun getHeroesList() = viewModelScope.launch {
        _uiState.value = CreateTeamUIState.LOADING
        val result = repository.getHeroesList()
        when (result) {
            is NetworkResult.Error -> errorHandler(result.code)
            is NetworkResult.Success -> {
                _uiState.value =
                    CreateTeamUIState.SUCCESS(result.data.map { hero ->
                        ActiveHeroInTeam.toActiveHero(hero)
                    })
            }
        }
    }

    private fun errorHandler(errorCode: Int) {
        when (errorCode) {
            105 -> _uiState.value = CreateTeamUIState.ERROR(R.string.check_your_internet_connection)
            else -> _uiState.value = CreateTeamUIState.ERROR(R.string.unknown_error)
        }
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
}