package com.example.honkaihelper.load_data

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentLoadDataBinding
import com.example.honkaihelper.equipment.EquipmentFragment
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.HeroesListFragment
import com.example.honkaihelper.utils.getSharedPrefVersion
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.visible

const val DATA_UPLOADED_KEY = "data_uploaded"

class LoadDataFragment : BaseFragment<FragmentLoadDataBinding>(FragmentLoadDataBinding::inflate) {

    private val viewModel by viewModels<LoadDataViewModel> { viewModelFactory }
    private val versionDB get() = requireArguments().getString(ARG_VERSION_DB)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.loadDataComponent().create().inject(this)
    }

    override fun setupView() {
        setupButtonRetryDownloadData()
        setupButtonGoBack()
    }

    override fun uiStateHandle() {
        viewModel.dataLoaded.observe(viewLifecycleOwner) {
            when(it) {
                LoadDataUiState.ERROR -> {
                    binding.groupDownloadData.gone()
                    binding.groupUnexpectedError.visible()
                }
                LoadDataUiState.LOADING -> {
                    binding.groupDownloadData.visible()
                    binding.groupUnexpectedError.gone()
                }
                LoadDataUiState.SUCCESS -> {
                    requireActivity().getSharedPrefVersion().edit().putString(KEY_VERSION_DB, versionDB).apply()
                    setFragmentResult(DATA_UPLOADED_KEY, HeroesListFragment.newInstance(true))
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun setupButtonRetryDownloadData() {
        binding.buttonRetryDownloadData.setOnClickListener {
            viewModel.getNewData()
        }
    }

    private fun setupButtonGoBack() {
        binding.buttonGoBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {

        private const val ARG_VERSION_DB = "version_db"
        private const val KEY_VERSION_DB = "VERSION_DB"

        fun newInstance(versionDB: String) : Bundle {
            return bundleOf(ARG_VERSION_DB to versionDB)
        }
    }
}