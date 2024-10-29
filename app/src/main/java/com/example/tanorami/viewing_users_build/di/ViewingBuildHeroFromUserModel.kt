package com.example.tanorami.viewing_users_build.di

import androidx.lifecycle.ViewModel
import com.example.tanorami.core.di.ViewModelKey
import com.example.tanorami.viewing_users_build.presentation.ViewingBuildHeroFromUserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewingBuildHeroFromUserModel {

    @Binds
    @[IntoMap ViewModelKey(ViewingBuildHeroFromUserViewModel::class)]
    fun bindViewModel(viewModel: ViewingBuildHeroFromUserViewModel): ViewModel
}