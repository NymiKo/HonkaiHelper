package com.example.tanorami

import android.app.Application
import com.example.tanorami.di.AppComponent
import com.example.tanorami.di.DaggerAppComponent

open class App: Application() {

    open val appComponent: AppComponent by lazy { initializeComponent() }

    private fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}