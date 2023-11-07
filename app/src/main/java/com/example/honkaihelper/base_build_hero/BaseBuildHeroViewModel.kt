package com.example.honkaihelper.base_build_hero

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.base_build_hero.data.BaseBuildHeroRepository
import javax.inject.Inject

class BaseBuildHeroViewModel @Inject constructor(
    private val repository: BaseBuildHeroRepository
): ViewModel() {

}