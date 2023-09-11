package com.example.honkaihelper.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.heroes.data.HeroesListRepository
import com.example.honkaihelper.models.Hero
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(
    private val repository: HeroesListRepository
) : ViewModel() {

    private val _heroesList = MutableLiveData<List<Hero>>(emptyList())
    val heroesList: LiveData<List<Hero>> = _heroesList

    init {
        getHeroesList()
    }

    private fun getHeroesList() {
        viewModelScope.launch {
            _heroesList.value = repository.getHeroesList()
        }
    }
}