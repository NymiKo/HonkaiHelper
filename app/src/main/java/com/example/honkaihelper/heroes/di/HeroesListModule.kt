package com.example.honkaihelper.heroes.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.heroes.HeroesListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HeroesListModule {

    @Binds
    @[IntoMap ViewModelKey(HeroesListViewModelImpl::class)]
    fun bindViewModel(viewModel: HeroesListViewModelImpl): ViewModel
}