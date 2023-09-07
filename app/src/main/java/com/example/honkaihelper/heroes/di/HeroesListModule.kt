package com.example.honkaihelper.heroes.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.heroes.HeroesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HeroesListModule {

    @Binds
    @IntoMap
    @ViewModelKey(HeroesListViewModel::class)
    fun bindViewModel(viewModel: HeroesListViewModel): ViewModel
}