package com.example.honkaihelper.di

import android.app.Application
import com.example.honkaihelper.di.components.AppComponent
import com.example.honkaihelper.di.components.DaggerAppComponent

class App: Application() {

    val appComponent: AppComponent by lazy { initializeComponent() }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}