package com.example.honkaihelper.base_build_hero.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.base_build_hero.InfoAboutHeroViewModel
import com.example.honkaihelper.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface InfoAboutHeroModule {
    @Binds
    @[IntoMap ViewModelKey(InfoAboutHeroViewModel::class)]
    fun bindViewModel(viewModel: InfoAboutHeroViewModel): ViewModel
}