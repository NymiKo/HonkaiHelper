package com.example.tanorami.builds_hero_from_users.presentation.models

import com.example.base.UiEffect

interface BuildsHeroFromUsersScreenSideEffects : UiEffect {
    class OnViewingBuildHeroFromUserScreen(val idBuild: Long): BuildsHeroFromUsersScreenSideEffects
    data object OnBack: BuildsHeroFromUsersScreenSideEffects
}