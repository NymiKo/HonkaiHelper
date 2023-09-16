package com.example.honkaihelper.teams

import android.util.Log
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
): ViewModel() {

    private val _uiState = MutableLiveData(TeamsUiState())
    val uiState: LiveData<TeamsUiState> = _uiState

    fun getTeamsList(idHero: Int) = viewModelScope.launch {
        _uiState.value = _uiState.value?.copy(isLoading = true)
        val result = repository.getTeamsList(idHero)
        result.onSuccess {
            _uiState.value = _uiState.value?.copy(teamsList = it)
        }.onFailure {

        }
        _uiState.value = _uiState.value?.copy(isLoading = false)
    }

}