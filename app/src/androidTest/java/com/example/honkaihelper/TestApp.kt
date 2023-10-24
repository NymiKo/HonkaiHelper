package com.example.honkaihelper

import androidx.test.platform.app.InstrumentationRegistry
import com.example.honkaihelper.di.AppComponent
import com.example.honkaihelper.di.DaggerAppComponent

class TestApp: App() {

    override val appComponent: AppComponent by lazy { initializeComponent() }

    private fun initializeComponent(): AppComponent {
        return DaggerTestAppComponent.factory().create(InstrumentationRegistry.getInstrumentation().targetContext)
    }

}