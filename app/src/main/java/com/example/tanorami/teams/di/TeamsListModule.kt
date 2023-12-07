package com.example.tanorami.teams.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.teams.TeamsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TeamsListModule {

    @Binds
    @[IntoMap ViewModelKey(TeamsListViewModel::class)]
    fun bindViewModel(viewModel: TeamsListViewModel): ViewModel
}