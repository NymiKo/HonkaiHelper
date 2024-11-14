package com.example.tanorami.teams_and_builds.presentation.models

import com.example.core.base.UiEffect

sealed interface TeamsAndBuildsScreenSideEffects : UiEffect {
    class OnViewingBuildHeroFromUserScreen(val idBuild: Long) : TeamsAndBuildsScreenSideEffects
}