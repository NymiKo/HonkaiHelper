package com.example.honkaihelper.create_build_hero.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.create_build_hero.CreateBuildHeroViewModel
import com.example.honkaihelper.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CreateBuildHeroModule {
    @Binds
    @[IntoMap ViewModelKey(CreateBuildHeroViewModel::class)]
    fun bindViewModel(viewModel: CreateBuildHeroViewModel): ViewModel
}