package com.example.tanorami.settings.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.example.tanorami.load_data.DATA_UPLOADED_KEY
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

    fun setupView() {
        setupToolbar()
        setupButtonCheckUpdate()
        setupButtonGoToFeedback()
        setupButtonDonate()
    }

    fun uiStateHandle() {
//        viewModel.uiState.observe(viewLifecycleOwner) {
//            when(it) {
//                is SettingsUIState.ERROR -> {
//                    binding.buttonCheckUpdate.visible()
//                    binding.progressCheckUpdate.gone()
//                    toast(requireActivity(), it.message)
//                }
//                is SettingsUIState.LATEST_VERSION -> {
//                    binding.buttonCheckUpdate.visible()
//                    binding.progressCheckUpdate.gone()
//                    toast(requireActivity(), R.string.already_have_latest_version_db)
//                }
//                is SettingsUIState.LOADING -> {
//                    binding.buttonCheckUpdate.invisible()
//                    binding.progressCheckUpdate.visible()
//                }
//                is SettingsUIState.NEW_VERSION -> {
//                    toast(requireActivity(), R.string.discovered_new_version_db)
//                    findNavController().navigate(R.id.loadDataFragment, LoadDataFragment.newInstance(it.versionDb))
//                }
//                is SettingsUIState.DATA_UPDATED -> {
//                    setupTextVersionDb()
//                }
//            }
//        }
    }

    private fun setupToolbar() {
//        binding.toolbarSettings.setNavigationOnClickListener {
//            findNavController().popBackStack()
//        }
    }

    private fun setupTextVersionDb() {
        //binding.textVersionDb.text = getString(R.string.version_db, getSharedPrefVersion().getString(
        //    KEY_VERSION_DB, ""))
    }

    private fun setupButtonCheckUpdate() {
//        binding.buttonCheckUpdate.setOnClickListener {
//            getSharedPrefVersion().getString(KEY_VERSION_DB, "")?.let { version -> viewModel.checkUpdate(version) }
//        }
    }

    private fun setupButtonGoToFeedback() {
//        binding.buttonGoToFeedback.setOnClickListener {
//            findNavController().navigate(R.id.sendFeedbackFragment)
//        }
    }

    private fun setupButtonDonate() {
//        binding.imageDonate.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.donationalerts.com/r/nymiko"))
            startActivity(intent)
//        }
    }

    companion object {
        private const val KEY_VERSION_DB = "VERSION_DB"
        private const val ARG_DATA_UPLOADED = "path"
    }
}