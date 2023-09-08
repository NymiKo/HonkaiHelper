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
): ViewModel() {

    private val _teamsList = MutableLiveData<List<TeamHero>>(emptyList())
    val teamsList: LiveData<List<TeamHero>> = _teamsList

    init {
        getTeamsList()
    }

    private fun getTeamsList() {
        viewModelScope.launch {
            _teamsList.value = repository.getTeamsList()
        }
    }

}