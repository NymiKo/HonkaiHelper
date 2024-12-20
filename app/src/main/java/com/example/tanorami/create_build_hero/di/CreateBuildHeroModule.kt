package com.example.tanorami.create_build_hero.di

import androidx.lifecycle.ViewModel
import com.example.base.ViewModelKey
import com.example.tanorami.create_build_hero.presentation.CreateBuildHeroViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CreateBuildHeroModule {
    @Binds
    @[IntoMap ViewModelKey(CreateBuildHeroViewModel::class)]
    fun bindViewModel(viewModel: CreateBuildHeroViewModel): ViewModel
}