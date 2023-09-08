package com.example.honkaihelper.createteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.createteam.data.CreateTeamRepository
import com.example.honkaihelper.models.ActiveHeroInTeam
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateTeamViewModel @Inject constructor(
    private val repository: CreateTeamRepository
): ViewModel() {

    private val _heroesList = MutableLiveData<List<ActiveHeroInTeam>>(emptyList())
    val heroesList: LiveData<List<ActiveHeroInTeam>> = _heroesList

    init {
        getHeroesList()
    }

    private fun getHeroesList() {
        viewModelScope.launch {
            _heroesList.value = repository.getHeroesList()
        }
    }
}