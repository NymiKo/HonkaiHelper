package com.example.tanorami.builds_hero_from_users.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.builds_hero_from_users.presentation.BuildsHeroFromUsersViewModel
import com.example.tanorami.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface BuildsHeroFromUsersModule {
    @Binds
    @[IntoMap ViewModelKey(BuildsHeroFromUsersViewModel::class)]
    fun bindViewModel(viewModel: BuildsHeroFromUsersViewModel): ViewModel
}