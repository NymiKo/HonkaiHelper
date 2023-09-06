package com.example.honkaihelper.di.components

import android.content.Context
import com.example.honkaihelper.activity.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
    fun inject(activity: MainActivity)

}