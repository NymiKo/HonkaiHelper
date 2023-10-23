package com.example.honkaihelper.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.ActivityMainBinding
import com.example.honkaihelper.utils.getSharedPrefVersion
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        (application as App).appComponent.mainComponent().create().inject(this)
        getSharedPrefVersion().getString(KEY_VERSION_DB, "")?.let { viewModel.getVersionDB(it) }
        uiStateHandle()

        setContentView(binding.root)
    }

    private fun uiStateHandle() {
        viewModel.uiState.observe(this) {
            when(it) {
                is MainUiState.DATA_NOT_CORRECTLY -> {
                    getSharedPrefVersion().edit().putString(KEY_VERSION_DB, it.version).apply()
                    findNavController(R.id.navHostFragment).navigate(R.id.loadDataFragment)
                }
                MainUiState.ERROR -> {

                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        const val KEY_VERSION_DB = "VERSION_DB"
    }
}