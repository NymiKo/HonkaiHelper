package com.example.tanorami.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.heroes.data.HeroesListRepository
import com.example.tanorami.heroes.data.model.Hero
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesListViewModelImpl @Inject constructor(
    private val repository: HeroesListRepository
) : HeroesListViewModel() {

    private val _uiState = MutableLiveData<HeroesUiState<List<Hero>>>(HeroesUiState.LOADING)
    val uiState: LiveData<HeroesUiState<List<Hero>>> = _uiState

    private val _heroesList = MutableLiveData<List<Hero>>()
    val heroesList: LiveData<List<Hero>> = _heroesList

    private val _avatar = MutableLiveData<String>()
    val avatar: LiveData<String> = _avatar

    init {
        getHeroesList()
    }

    override fun getHeroesList() = viewModelScope.launch {
        _uiState.value = HeroesUiState.LOADING
        val result = repository.getHeroesList()
        if (result.isNotEmpty()) {
            _uiState.value = HeroesUiState.SUCCESS(result)
            _heroesList.value = result
        } else {
            _uiState.value = HeroesUiState.ERROR
        }
    }

    override fun getAvatar() = viewModelScope.launch {
        when (val result = repository.getAvatar()) {
            is NetworkResult.Error -> {

            }

            is NetworkResult.Success -> {
                _avatar.value = result.data
            }
        }
    }
}