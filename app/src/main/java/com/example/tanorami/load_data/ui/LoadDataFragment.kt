package com.example.tanorami.load_data.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tanorami.App
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.load_data.presentation.LoadDataViewModel
import javax.inject.Inject

const val DATA_UPLOADED_KEY = "data_uploaded"

class LoadDataFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<LoadDataViewModel> { viewModelFactory }
    private val versionDB get() = requireArguments().getString(ARG_VERSION_DB)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.loadDataComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    LoadDataScreen(
                        newVersionDB = versionDB ?: "",
                        viewModel = viewModel,
                        navController = findNavController()
                    )
                }
            }
        }
    }

    fun setupView() {
        setupButtonRetryDownloadData()
        setupButtonGoBack()
    }

    fun uiStateHandle() {
//        viewModel.dataLoaded.observe(viewLifecycleOwner) {
//            when(it) {
//                LoadDataUiState.ERROR -> {
//                    binding.groupDownloadData.gone()
//                    binding.groupUnexpectedError.visible()
//                }
//                LoadDataUiState.LOADING -> {
//                    binding.groupDownloadData.visible()
//                    binding.groupUnexpectedError.gone()
//                }
//                LoadDataUiState.SUCCESS -> {
//                    setFragmentResult(DATA_UPLOADED_KEY, bundleOf(ARG_DATA_UPLOADED to true))
//                    findNavController().popBackStack()
//                }
//            }
//        }
    }

    private fun setupButtonRetryDownloadData() {
//        binding.buttonRetryDownloadData.setOnClickListener {
//            viewModel.getNewData(versionDB)
//        }
    }

    private fun setupButtonGoBack() {
//        binding.buttonGoBack.setOnClickListener {
//            findNavController().popBackStack()
//        }
    }

    companion object {

        private const val ARG_VERSION_DB = "version_db"
        private const val ARG_DATA_UPLOADED = "path"

        fun newInstance(versionDB: String) : Bundle {
            return bundleOf(ARG_VERSION_DB to versionDB)
        }
    }
}