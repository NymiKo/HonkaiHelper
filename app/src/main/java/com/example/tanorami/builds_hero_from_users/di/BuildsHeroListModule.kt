package com.example.tanorami.builds_hero_from_users.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.builds_hero_from_users.BuildsHeroListViewModel
import com.example.tanorami.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BuildsHeroListModule {
    @Binds
    @[IntoMap ViewModelKey(BuildsHeroListViewModel::class)]
    fun bindViewModel(viewModel: BuildsHeroListViewModel): ViewModel
}