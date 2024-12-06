package com.example.teams_and_builds.presentation.models

import com.example.base.UiEffect

internal sealed interface TeamsAndBuildsScreenSideEffects : UiEffect {
    class OnViewingBuildHeroFromUserScreen(val idBuild: Long) : TeamsAndBuildsScreenSideEffects
}