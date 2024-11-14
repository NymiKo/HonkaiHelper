package com.example.tanorami.create_build_heroes_list.di

import androidx.lifecycle.ViewModel
import com.example.core.di.ViewModelKey
import com.example.tanorami.create_build_heroes_list.presentation.CreateBuildHeroesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CreateBuildHeroesListModule {
    @Binds
    @[IntoMap ViewModelKey(CreateBuildHeroesListViewModel::class)]
    fun bindViewModel(viewModel: CreateBuildHeroesListViewModel): ViewModel
}