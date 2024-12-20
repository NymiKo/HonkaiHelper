package com.example.tanorami.createteam.di

import androidx.lifecycle.ViewModel
import com.example.base.ViewModelKey
import com.example.tanorami.createteam.presentation.CreateTeamViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CreateTeamModule {
    @Binds
    @[IntoMap ViewModelKey(CreateTeamViewModel::class)]
    fun bindViewModel(viewModel: CreateTeamViewModel): ViewModel
}