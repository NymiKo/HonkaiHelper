package com.example.tanorami.heroes.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.core.di.ViewModelKey
import com.example.tanorami.heroes.presentation.HeroesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HeroesListModule {

    @Binds
    @[IntoMap ViewModelKey(HeroesListViewModel::class)]
    fun bindViewModel(viewModel: HeroesListViewModel): ViewModel
}