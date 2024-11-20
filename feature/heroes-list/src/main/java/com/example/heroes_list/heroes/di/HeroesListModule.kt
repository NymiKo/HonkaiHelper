package com.example.heroes_list.heroes.di

import androidx.lifecycle.ViewModel
import com.example.base.ViewModelKey
import com.example.di.scopes.FeatureScope
import com.example.heroes_list.heroes.presentation.HeroesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HeroesListModule {

    @Binds
    @FeatureScope
    @[IntoMap ViewModelKey(HeroesListViewModel::class)]
    fun bindViewModel(viewModel: HeroesListViewModel): ViewModel
}