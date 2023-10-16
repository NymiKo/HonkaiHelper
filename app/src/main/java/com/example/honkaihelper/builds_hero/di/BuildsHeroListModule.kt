package com.example.honkaihelper.builds_hero.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.builds_hero.BuildsHeroListViewModel
import com.example.honkaihelper.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BuildsHeroListModule {
    @Binds
    @[IntoMap ViewModelKey(BuildsHeroListViewModel::class)]
    fun bindViewModel(viewModel: BuildsHeroListViewModel): ViewModel
}