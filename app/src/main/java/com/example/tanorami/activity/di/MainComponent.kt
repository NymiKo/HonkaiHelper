package com.example.tanorami.activity.di

import com.example.tanorami.activity.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}