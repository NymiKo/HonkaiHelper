package com.example.honkaihelper.setupteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.setupteam.data.model.SetupHero
import kotlinx.coroutines.launch
import javax.inject.Inject

class SetupTeamViewModel @Inject constructor() : ViewModel() {

    private val _heroesList = MutableLiveData<List<SetupHero>>()
    val heroesList: LiveData<List<SetupHero>> = _heroesList

    fun setHeroesList(heroesList: List<Hero>)  = viewModelScope.launch {
        _heroesList.value = heroesList.map { SetupHero(hero = it, level = 0, weapon = null) }
    }

    fun updateHero(setupHero: SetupHero) {
        val currentList = _heroesList.value ?: emptyList()
        val newList = currentList.toMutableList()
        newList[currentList.indexOf(setupHero)].copy(
            hero = setupHero.hero,
            level = setupHero.level,
            eidolon = setupHero.eidolon,
            weapon = setupHero.weapon
        )
        _heroesList.value = newList
    }
}