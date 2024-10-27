package com.example.tanorami.heroes.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.heroes.presentation.HeroesListViewMode
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HeroesListModule {

    @Binds
    @[IntoMap ViewModelKey(HeroesListViewMode::class)]
    fun bindViewModel(viewModel: HeroesListViewMode): ViewModel
}