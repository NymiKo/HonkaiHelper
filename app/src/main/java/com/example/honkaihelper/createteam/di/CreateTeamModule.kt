package com.example.honkaihelper.createteam.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.createteam.CreateTeamViewModel
import com.example.honkaihelper.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CreateTeamModule {
    @Binds
    @[IntoMap ViewModelKey(CreateTeamViewModel::class)]
    fun bindViewModel(viewModel: CreateTeamViewModel): ViewModel
}