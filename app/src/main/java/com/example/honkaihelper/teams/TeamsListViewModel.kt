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

    private val _uiState = MutableLiveData<TeamsUiState>()
    val uiState: LiveData<TeamsUiState> = _uiState

    private val _teamsList = MutableLiveData<List<TeamHero>>(emptyList())
    val teamsList: LiveData<List<TeamHero>> = _teamsList

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean> = _isError

    fun getTeamsList(idHero: Int) = viewModelScope.launch {
        _isLoading.value = true
        val result = repository.getTeamsList(idHero)
        result.onSuccess {
            _uiState.value = _uiState.value?.copy(teamsList = it)
            _isError.value = false
        }.onFailure {
            _isError.value = true
        }
        _isLoading.value = false
    }

}