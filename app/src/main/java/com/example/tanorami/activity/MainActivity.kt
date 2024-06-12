package com.example.tanorami.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.databinding.ActivityMainBinding
import com.example.tanorami.load_data.LoadDataFragment
import com.example.tanorami.utils.getSharedPrefVersion
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
                    findNavController(R.id.navHostFragment).navigate(R.id.loadDataFragment, LoadDataFragment.newInstance(it.version))
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