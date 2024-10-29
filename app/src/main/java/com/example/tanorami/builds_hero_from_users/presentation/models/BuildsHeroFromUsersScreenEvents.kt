package com.example.tanorami.builds_hero_from_users.presentation.models

import com.example.tanorami.base.UiEvent

sealed interface BuildsHeroFromUsersScreenEvents : UiEvent {
    class GetBuildsHeroList(val idHero: Int): BuildsHeroFromUsersScreenEvents
    class OnViewingBuildHeroFromUserScreen(val idBuild: Long): BuildsHeroFromUsersScreenEvents
    data object OnBack: BuildsHeroFromUsersScreenEvents
}