package com.example.tanorami.teams.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.tanorami.teams.presentation.TeamsFromUsersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TeamsFromUsersModule {

    @Binds
    @[IntoMap ViewModelKey(TeamsFromUsersViewModel::class)]
    fun bindViewModel(viewModel: TeamsFromUsersViewModel): ViewModel
}