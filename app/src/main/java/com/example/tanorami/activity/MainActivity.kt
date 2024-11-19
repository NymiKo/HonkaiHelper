package com.example.tanorami.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.tanorami.App
import com.example.tanorami.navigation.AppNavigation
import com.example.ui.theme.AppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AppNavigation(
                    viewModelFactory = viewModelFactory,
                )
            }
        }
    }
}