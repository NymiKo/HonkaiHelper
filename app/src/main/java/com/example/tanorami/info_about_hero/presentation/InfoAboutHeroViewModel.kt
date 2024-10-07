package com.example.tanorami.info_about_hero.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.info_about_hero.data.InfoAboutHeroRepository
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenEvents
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenSideEffects
import com.example.tanorami.info_about_hero.presentation.models.InfoAboutHeroScreenUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoAboutHeroViewModel @Inject constructor(
    private val repository: InfoAboutHeroRepository
): BaseViewModel<InfoAboutHeroScreenUiState, InfoAboutHeroScreenEvents, InfoAboutHeroScreenSideEffects>(
    initialState = InfoAboutHeroScreenUiState()
) {

    override fun onEvent(event: InfoAboutHeroScreenEvents) {
        when(event) {
            is InfoAboutHeroScreenEvents.GetHeroInfo -> getHeroInfo(event.idHero)
            InfoAboutHeroScreenEvents.OnBack -> {
                sendSideEffect(InfoAboutHeroScreenSideEffects.OnBack)
            }
            InfoAboutHeroScreenEvents.OnBaseBuildHeroScreen -> {
                uiState.hero?.let {
                    sendSideEffect(InfoAboutHeroScreenSideEffects.OnBaseBuildHeroScreen(it.id))
                }
            }
            InfoAboutHeroScreenEvents.OnTeamsListScreen -> {
                uiState.hero?.let {
                    sendSideEffect(InfoAboutHeroScreenSideEffects.OnTeamsListScreen(it.id))
                }
            }
        }
    }

    private fun getHeroInfo(idHero: Int) = viewModelScope.launch {
        val result = repository.getHero(idHero)
        uiState = uiState.copy(
            hero = result.hero,
            path = result.path,
            element = result.element,
            abilitiesList = result.abilitiesList,
            eidolonsList = result.eidolonsList
        )
    }
}