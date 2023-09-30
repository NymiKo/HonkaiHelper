package com.example.honkaihelper.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.heroes.data.HeroesListRepository
import com.example.honkaihelper.heroes.data.model.Hero
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesListViewModel @Inject constructor(
    private val repository: HeroesListRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<HeroesUiState<List<Hero>>>(HeroesUiState.LOADING)
    val uiState: LiveData<HeroesUiState<List<Hero>>> = _uiState

    private val _heroesList = MutableLiveData<List<Hero>>()
    val heroesList: LiveData<List<Hero>> = _heroesList

    private val _avatar = MutableLiveData<String>()
    val avatar: LiveData<String> = _avatar

    init {
        getHeroesList()
    }

    fun getHeroesList() = viewModelScope.launch {
        _uiState.value = HeroesUiState.LOADING
        val result = repository.getHeroesList()
        when (result) {
            is NetworkResult.Error -> _uiState.value = HeroesUiState.ERROR
            is NetworkResult.Success -> {
                _uiState.value = HeroesUiState.SUCCESS(result.data)
                _heroesList.value = result.data!!
            }
        }
    }

    fun getAvatar() = viewModelScope.launch {
        val result = repository.getAvatar()
        when(result) {
            is NetworkResult.Error -> {

            }
            is NetworkResult.Success -> {
                _avatar.value = result.data!!
            }
        }
    }
}