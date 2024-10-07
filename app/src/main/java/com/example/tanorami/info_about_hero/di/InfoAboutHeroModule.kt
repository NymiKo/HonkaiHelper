package com.example.tanorami.info_about_hero.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.info_about_hero.presentation.InfoAboutHeroViewModel
import com.example.tanorami.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface InfoAboutHeroModule {
    @Binds
    @[IntoMap ViewModelKey(InfoAboutHeroViewModel::class)]
    fun bindViewModel(viewModel: InfoAboutHeroViewModel): ViewModel
}