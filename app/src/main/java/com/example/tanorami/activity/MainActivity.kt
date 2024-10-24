package com.example.tanorami.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.tanorami.App
import com.example.tanorami.activity.presentation.MainViewModel
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.navigation.AppNavigation
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        (application as App).appComponent.mainComponent().create().inject(this)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                AppNavigation(
                    profileAvatar = viewModel.avatar.collectAsState().value,
                    viewModelFactory = viewModelFactory,
                )
            }
        }
    }
}