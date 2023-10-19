package com.example.honkaihelper.load_data.di

import com.example.honkaihelper.load_data.LoadDataFragment
import dagger.Subcomponent

@Subcomponent(modules = [LoadDataModule::class])
interface LoadDataComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoadDataComponent
    }

    fun inject(fragment: LoadDataFragment)
}