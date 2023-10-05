package com.example.honkaihelper.heroes

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FakeHeroesListViewModel: HeroesListViewModel() {

    val state = HeroesUiState.LOADING

    override fun getHeroesList(): Job = viewModelScope.launch {
        state
    }

    override fun getAvatar(): Job {

    }
}