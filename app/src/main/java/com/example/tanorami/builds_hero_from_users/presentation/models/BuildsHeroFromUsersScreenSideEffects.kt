package com.example.tanorami.builds_hero_from_users.presentation.models

import com.example.tanorami.base.UiEffect

interface BuildsHeroFromUsersScreenSideEffects : UiEffect {
    data object OnCreateBuildHeroScreen: BuildsHeroFromUsersScreenSideEffects
    class OnViewingBuildHeroFromUserScreen(val idBuild: Long): BuildsHeroFromUsersScreenSideEffects
    data object OnBack: BuildsHeroFromUsersScreenSideEffects
}