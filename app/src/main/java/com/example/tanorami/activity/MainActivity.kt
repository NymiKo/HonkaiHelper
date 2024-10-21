package com.example.tanorami.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.tanorami.App
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.navigation.main.MainScreen
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        (application as App).appComponent.mainComponent().create().inject(this)
        uiStateHandle()
        setContent {
            AppTheme {
                MainScreen(
                    profileAvatar = viewModel.avatar.collectAsState().value,
                    viewModelFactory = viewModelFactory
                )
            }
        }
    }

    private fun uiStateHandle() {
//        viewModel.uiState.observe(this) {
//            when(it) {
//                is MainUiState.DATA_NOT_CORRECTLY -> {
//                    findNavController(R.id.navHostFragment).navigate(R.id.loadDataFragment, LoadDataFragment.newInstance(it.version))
//                }
//                MainUiState.ERROR -> {
//
//                }
//            }
//        }
    }
}