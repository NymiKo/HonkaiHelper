package com.example.honkaihelper.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.teams.data.TeamsListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamsListViewModel @Inject constructor(
    private val repository: TeamsListRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<TeamsUiState<List<TeamHero>>>(TeamsUiState.LOADING)
    val uiState: LiveData<TeamsUiState<List<TeamHero>>> = _uiState

    private val _nameHero = MutableLiveData<String>()
    val nameHero: LiveData<String> = _nameHero

    fun getNameHero(idHero: Int) = viewModelScope.launch {
        _nameHero.value = repository.getNameHero(idHero)
    }

    fun getTeamsList(idHero: Int) = viewModelScope.launch {
        _uiState.value = TeamsUiState.LOADING
        val result = repository.getTeamsList(idHero)
        when (result) {
            is NetworkResult.Error -> _uiState.value = TeamsUiState.ERROR
            is NetworkResult.Success -> {
                if (result.data.isEmpty()) _uiState.value = TeamsUiState.EMPTY
                else _uiState.value = TeamsUiState.SUCCESS(result.data)
            }
        }
    }
}