package com.example.tanorami.base_build_hero.presentation

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.tanorami.base_build_hero.data.BaseBuildHeroRepository
import com.example.tanorami.base_build_hero.presentation.models.BaseBuildHeroScreenEvents
import com.example.tanorami.base_build_hero.presentation.models.BaseBuildHeroScreenSideEffects
import com.example.tanorami.base_build_hero.presentation.models.BaseBuildHeroScreenSideEffects.*
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
            BaseBuildHeroScreenEvents.OnBack -> sendSideEffect(OnBack)
            BaseBuildHeroScreenEvents.OnBuildsHeroFromUsersScreen -> sendSideEffect(
                OnBuildsHeroFromUsersScreen(uiState.fullBaseBuildHero.idHero)
            )
            is BaseBuildHeroScreenEvents.OnInfoAboutDecorationScreen -> {
                sendSideEffect(OnInfoAboutDecorationScreen(event.idDecoration))
            }
            is BaseBuildHeroScreenEvents.OnInfoAboutRelicScreen -> {
                sendSideEffect(OnInfoAboutRelicScreen(event.idRelic))
            }
            is BaseBuildHeroScreenEvents.OnInfoAboutWeaponScreen -> {
                sendSideEffect(OnInfoAboutWeaponScreen(event.idWeapon))
            }

            is BaseBuildHeroScreenEvents.ChangeVisibilityRemarkDialog ->
                uiState = uiState.copy(
                    visibilityRemarkDialog = event.visibility,
                    remarkTextInRemarkDialog = event.remark
                )
        }
    }

    private fun getFullBaseBuildHero(idHero: Int) = viewModelScope.launch {
        val result = repository.getBaseBuildHero(idHero)
        uiState = uiState.copy(fullBaseBuildHero = result)
    }
}