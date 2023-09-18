package com.example.honkaihelper.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.models.TeamHero
import com.example.honkaihelper.teams.data.TeamsListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamsListViewModel @Inject constructor(
    private val repository: TeamsListRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<TeamsUiState<List<TeamHero>>>(TeamsUiState.IDLE)
    val uiState: LiveData<TeamsUiState<List<TeamHero>>> = _uiState

    fun getTeamsList(idHero: Int) = viewModelScope.launch {
        _uiState.value = TeamsUiState.LOADING
        val result = repository.getTeamsList(idHero)
        result.onSuccess {
            _uiState.value = TeamsUiState.SUCCESS(it)
        }.onFailure {
            _uiState.value = TeamsUiState.ERROR
        }
    }

}