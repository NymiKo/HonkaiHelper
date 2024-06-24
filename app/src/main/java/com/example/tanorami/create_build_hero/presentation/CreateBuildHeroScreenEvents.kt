package com.example.tanorami.create_build_hero.presentation

sealed interface CreateBuildHeroScreenEvents {
    data object OnBack: CreateBuildHeroScreenEvents
    data object DeleteBuild: CreateBuildHeroScreenEvents
    data object SaveBuild: CreateBuildHeroScreenEvents
    data object UpdateBuild: CreateBuildHeroScreenEvents
}