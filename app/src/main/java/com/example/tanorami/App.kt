package com.example.tanorami

import android.app.Application
import com.example.tanorami.core.di.AppComponent
import com.example.tanorami.core.di.DaggerAppComponent

open class App: Application() {

    open val appComponent: AppComponent by lazy { initializeComponent() }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}