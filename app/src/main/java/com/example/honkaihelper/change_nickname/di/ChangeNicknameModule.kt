package com.example.honkaihelper.change_nickname.di

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.change_nickname.ChangeNicknameViewModel
import com.example.honkaihelper.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ChangeNicknameModule {
    @Binds
    @[IntoMap ViewModelKey(ChangeNicknameViewModel::class)]
    fun bindViewModel(viewModel: ChangeNicknameViewModel): ViewModel
}