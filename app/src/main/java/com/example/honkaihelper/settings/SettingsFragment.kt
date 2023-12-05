package com.example.honkaihelper.settings

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.activity.MainActivity
import com.example.honkaihelper.base.BaseFragment
import com.example.honkaihelper.databinding.FragmentSettingsBinding
import com.example.honkaihelper.utils.getSharedPrefUser
import com.example.honkaihelper.utils.getSharedPrefVersion
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.invisible
import com.example.honkaihelper.utils.toast
import com.example.honkaihelper.utils.visible

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val viewModel by viewModels<SettingsViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.settingsComponent().create().inject(this)
    }

    override fun setupView() {
        setupButtonCheckUpdate()
        setupTextVersionDb()
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
                is SettingsUIState.ERROR -> {
                    binding.buttonCheckUpdate.visible()
                    binding.progressCheckUpdate.gone()
                    toast(requireActivity(), it.message)
                }
                is SettingsUIState.LATEST_VERSION -> {
                    binding.buttonCheckUpdate.visible()
                    binding.progressCheckUpdate.gone()
                    toast(requireActivity(), R.string.already_have_latest_version_db)
                }
                is SettingsUIState.LOADING -> {
                    binding.buttonCheckUpdate.invisible()
                    binding.progressCheckUpdate.visible()
                }
                is SettingsUIState.NEW_VERSION -> {
                    toast(requireActivity(), R.string.discovered_new_version_db)
                    findNavController().navigate(R.id.loadDataFragment)
                }
            }
        }
    }

    private fun setupTextVersionDb() {
        binding.textVersionDb.text = getString(R.string.version_db, getSharedPrefVersion().getString(KEY_VERSION_DB, ""))
    }

    private fun setupButtonCheckUpdate() {
        binding.buttonCheckUpdate.setOnClickListener {
            getSharedPrefVersion().getString(KEY_VERSION_DB, "")?.let { version -> viewModel.checkUpdate(version) }
        }
    }

    companion object {
        const val KEY_VERSION_DB = "VERSION_DB"
    }
}