package com.example.honkaihelper.profile.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.di.ViewModelKey
import com.example.honkaihelper.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProfileModule {
    @Binds
    @[IntoMap ViewModelKey(ProfileViewModel::class)]
    fun bindViewModel(viewModel: ProfileViewModel): ViewModel
}