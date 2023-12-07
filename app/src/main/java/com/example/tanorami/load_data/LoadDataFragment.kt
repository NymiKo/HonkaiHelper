package com.example.tanorami.load_data

import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tanorami.App
import com.example.tanorami.databinding.FragmentLoadDataBinding
import com.example.tanorami.base.BaseFragment
import com.example.tanorami.heroes.HeroesListFragment
import com.example.tanorami.utils.getSharedPrefVersion
import com.example.tanorami.utils.gone
import com.example.tanorami.utils.visible

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
                    setFragmentResult(DATA_UPLOADED_KEY, bundleOf(ARG_DATA_UPLOADED to true))
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
        private const val ARG_DATA_UPLOADED = "path"

        fun newInstance(versionDB: String) : Bundle {
            return bundleOf(ARG_VERSION_DB to versionDB)
        }
    }
}