package com.example.tanorami.createteam.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.createteam.presentation.CreateTeamViewModel
import com.example.tanorami.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CreateTeamModule {
    @Binds
    @[IntoMap ViewModelKey(CreateTeamViewModel::class)]
    fun bindViewModel(viewModel: CreateTeamViewModel): ViewModel
}