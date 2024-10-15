package com.example.tanorami.viewing_users_build.presentation

import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.base.BaseViewModel
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.viewing_users_build.data.ViewingUsersBuildRepository
import com.example.tanorami.viewing_users_build.presentation.models.ViewingBuildHeroFromUserScreenEvents
import com.example.tanorami.viewing_users_build.presentation.models.ViewingBuildHeroFromUserScreenSideEffects
import com.example.tanorami.viewing_users_build.presentation.models.ViewingBuildHeroFromUserScreenUiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewingBuildHeroFromUserViewModel @Inject constructor(
    private val repository: ViewingUsersBuildRepository
) : BaseViewModel<ViewingBuildHeroFromUserScreenUiState, ViewingBuildHeroFromUserScreenEvents, ViewingBuildHeroFromUserScreenSideEffects>(
    initialState = ViewingBuildHeroFromUserScreenUiState()
) {

    override fun onEvent(event: ViewingBuildHeroFromUserScreenEvents) {
        when (event) {
            is ViewingBuildHeroFromUserScreenEvents.GetHeroBuild -> getHeroBuild(event.idBuild, event.uid)

            ViewingBuildHeroFromUserScreenEvents.OnBack -> sendSideEffect(
                ViewingBuildHeroFromUserScreenSideEffects.OnBack
            )

            ViewingBuildHeroFromUserScreenEvents.OnCopyUIDClick -> sendSideEffect(
                ViewingBuildHeroFromUserScreenSideEffects.OnCopyUIDClick
            )

            ViewingBuildHeroFromUserScreenEvents.OnInfoAboutDecorationScreen -> sendSideEffect(
                ViewingBuildHeroFromUserScreenSideEffects.OnInfoAboutDecorationScreen(
                    uiState.buildHero.decoration?.idDecoration ?: 0
                )
            )

            ViewingBuildHeroFromUserScreenEvents.OnTwoPartRelicClick -> sendSideEffect(
                ViewingBuildHeroFromUserScreenSideEffects.OnInfoAboutRelicScreen(
                    uiState.buildHero.relicTwoParts?.idRelic ?: 0
                )
            )

            ViewingBuildHeroFromUserScreenEvents.OnFourPartRelicClick -> sendSideEffect(
                ViewingBuildHeroFromUserScreenSideEffects.OnInfoAboutRelicScreen(
                    uiState.buildHero.relicFourParts?.idRelic ?: 0
                )
            )

            ViewingBuildHeroFromUserScreenEvents.OnInfoAboutWeaponScreen -> sendSideEffect(
                ViewingBuildHeroFromUserScreenSideEffects.OnInfoAboutWeaponScreen(
                    uiState.buildHero.weapon?.idWeapon ?: 0
                )
            )
        }
    }

    private fun getHeroBuild(idBuild: Long, uid: String) = viewModelScope.launch {
        val result = if (uid.isEmpty()) {
            repository.getHeroBuildByID(idBuild)
        } else {
            repository.getHeroBuildByUID(uid)
        }

        when (result) {
            is NetworkResult.Error -> {
                uiState = uiState.copy(
                    isSuccess = false,
                    isError = true,
                    errorMessage = errorHandler(result.code)
                )
            }

            is NetworkResult.Success -> {
                uiState = uiState.copy(
                    isSuccess = true,
                    isError = false,
                    buildHero = result.data
                )
            }
        }
    }

    private fun errorHandler(errorCode: Int): Int {
        return when (errorCode) {
            105 -> R.string.check_your_internet_connection
            else -> R.string.error
        }
    }
}