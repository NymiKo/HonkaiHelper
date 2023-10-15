package com.example.honkaihelper.setupteam.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.setupteam.SetupTeamViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SetupTeamModule {

    @Binds
    @[IntoMap ViewModelKey(SetupTeamViewModel::class)]
    fun bindViewModel(viewModel: SetupTeamViewModel): ViewModel
}