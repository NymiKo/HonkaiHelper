package com.example.tanorami.base_build_hero.di

import androidx.lifecycle.ViewModel
import com.example.base.ViewModelKey
import com.example.tanorami.base_build_hero.presentation.BaseBuildHeroViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BaseBuildHeroModule {

    @Binds
    @[IntoMap ViewModelKey(BaseBuildHeroViewModel::class)]
    fun bindViewModel(viewModel: BaseBuildHeroViewModel): ViewModel
}