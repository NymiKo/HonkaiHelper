package com.example.tanorami.heroes.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.di.ViewModelKey
import com.example.tanorami.heroes.HeroesListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HeroesListModule {

    @Binds
    @[IntoMap ViewModelKey(HeroesListViewModelImpl::class)]
    fun bindViewModel(viewModel: HeroesListViewModelImpl): ViewModel
}