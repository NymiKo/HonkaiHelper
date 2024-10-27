package com.example.tanorami.info_about_decoration.ui

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
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.info_about_decoration.presentation.InfoAboutDecorationViewModel
import javax.inject.Inject

class InfoAboutDecorationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val idDecoration get() = requireArguments().getInt(ID_DECORATION)
    private val viewModel by viewModels<InfoAboutDecorationViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //(requireActivity().application as App).appComponent.decorationInfoComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDecoration(idDecoration)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {

                }
            }
        }
    }

    companion object {
        private const val ID_DECORATION = "id_decoration"
        fun newInstance(idDecoration: Int): Bundle {
            return bundleOf(ID_DECORATION to idDecoration)
        }
    }
}