package com.example.tanorami.base_build_hero.presentation

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.tanorami.base_build_hero.data.BaseBuildHeroRepository
import com.example.tanorami.base_build_hero.presentation.models.BaseBuildHeroScreenEvents
import com.example.tanorami.base_build_hero.presentation.models.BaseBuildHeroScreenSideEffects
import com.example.tanorami.base_build_hero.presentation.models.BaseBuildHeroScreenUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class BaseBuildHeroViewModel @Inject constructor(
    private val repository: BaseBuildHeroRepository
) : BaseViewModel<BaseBuildHeroScreenUiState, BaseBuildHeroScreenEvents, BaseBuildHeroScreenSideEffects>(
    initialState = BaseBuildHeroScreenUiState()
) {
    override fun onEvent(event: BaseBuildHeroScreenEvents) {
        when (event) {
            is BaseBuildHeroScreenEvents.GetFullBaseBuildHero -> getFullBaseBuildHero(event.idHero)
            BaseBuildHeroScreenEvents.OnBack -> sendSideEffect(BaseBuildHeroScreenSideEffects.OnBack)
            BaseBuildHeroScreenEvents.OnBuildsHeroFromUsersScreen -> sendSideEffect(
                BaseBuildHeroScreenSideEffects.OnBuildsHeroFromUsersScreen(uiState.idHero)
            )
            is BaseBuildHeroScreenEvents.OnInfoAboutDecorationScreen -> {
                sendSideEffect(BaseBuildHeroScreenSideEffects.OnInfoAboutDecorationScreen(event.idDecoration))
            }
            is BaseBuildHeroScreenEvents.OnInfoAboutRelicScreen -> {
                sendSideEffect(BaseBuildHeroScreenSideEffects.OnInfoAboutRelicScreen(event.idRelic))
            }
            is BaseBuildHeroScreenEvents.OnInfoAboutWeaponScreen -> {
                sendSideEffect(BaseBuildHeroScreenSideEffects.OnInfoAboutWeaponScreen(event.idWeapon))
            }
        }
    }

    private fun getFullBaseBuildHero(idHero: Int) = viewModelScope.launch {
        val result = repository.getBaseBuildHero(idHero)
        uiState = uiState.copy(
            idHero = idHero,
            weaponsList = result.weapons,
            relicsList = result.relics,
            decorationsList = result.decoration,
            buildStatsEquipment = result.buildStatsEquipment
        )
    }
}