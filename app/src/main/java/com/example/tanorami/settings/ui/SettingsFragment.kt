package com.example.tanorami.settings.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tanorami.App
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.load_data.ui.DATA_UPLOADED_KEY
import com.example.tanorami.settings.presentation.SettingsViewModel
import com.example.tanorami.settings.presentation.models.SettingsScreenEvents
import javax.inject.Inject

class SettingsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<SettingsViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.settingsComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(DATA_UPLOADED_KEY) { _, bundle ->
            if (bundle.getBoolean(ARG_DATA_UPLOADED)) {
                viewModel.onEvent(SettingsScreenEvents.DataUpdated)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    SettingsScreen(viewModel = viewModel, navController = findNavController())
                }
            }
        }
    }

    companion object {
        private const val ARG_DATA_UPLOADED = "path"
    }
}