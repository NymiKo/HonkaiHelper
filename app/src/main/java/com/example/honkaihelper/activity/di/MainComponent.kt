package com.example.honkaihelper.activity.di

import com.example.honkaihelper.activity.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}