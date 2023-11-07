package com.example.honkaihelper.base_build_hero.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.base_build_hero.BaseBuildHeroViewModel
import com.example.honkaihelper.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BaseBuildHeroModule {

    @Binds
    @[IntoMap ViewModelKey(BaseBuildHeroViewModel::class)]
    fun bindViewModel(viewModel: BaseBuildHeroViewModel): ViewModel
}