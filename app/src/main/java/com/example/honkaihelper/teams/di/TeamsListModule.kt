package com.example.honkaihelper.teams.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.teams.TeamsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TeamsListModule {

    @Binds
    @[IntoMap ViewModelKey(TeamsListViewModel::class)]
    fun bindViewModel(viewModel: TeamsListViewModel): ViewModel
}