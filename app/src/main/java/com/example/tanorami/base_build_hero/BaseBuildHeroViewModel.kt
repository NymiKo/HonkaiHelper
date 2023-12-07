package com.example.tanorami.base_build_hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.base_build_hero.data.BaseBuildHeroRepository
import com.example.tanorami.base_build_hero.data.model.FullBaseBuildHero
import kotlinx.coroutines.launch
import javax.inject.Inject

class BaseBuildHeroViewModel @Inject constructor(
    private val repository: BaseBuildHeroRepository
): ViewModel() {
    private val _fullBaseBuildHero = MutableLiveData<FullBaseBuildHero>()
    val fullBaseBuildHero: LiveData<FullBaseBuildHero> = _fullBaseBuildHero

    fun getFullBaseBuildHero(idHero: Int) = viewModelScope.launch {
        _fullBaseBuildHero.value = repository.getBaseBuildHero(idHero)
    }
}