package com.example.tanorami.teams_and_builds.presentation.models

import com.example.base.UiEffect

sealed interface TeamsAndBuildsScreenSideEffects : UiEffect {
    class OnViewingBuildHeroFromUserScreen(val idBuild: Long) : TeamsAndBuildsScreenSideEffects
}