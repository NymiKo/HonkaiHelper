package com.example.tanorami.teams_and_builds.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.core.di.ViewModelKey
import com.example.tanorami.teams_and_builds.presentation.TeamsAndBuildsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TeamsAndBuildsModule {
    @Binds
    @[IntoMap ViewModelKey(TeamsAndBuildsViewModel::class)]
    fun bindViewModel(viewModel: TeamsAndBuildsViewModel): ViewModel
}