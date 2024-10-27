package com.example.tanorami.info_about_relic.ui

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
import com.example.tanorami.info_about_relic.presentation.InfoAboutRelicViewModel
import javax.inject.Inject

class InfoAboutRelicFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val idRelic get() = requireArguments().getInt(ID_RELIC)
    private val viewModel by viewModels<InfoAboutRelicViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //(requireActivity().application as App).appComponent.relicInfoComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRelic(idRelic)
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
        private const val ID_RELIC = "id_relic"

        fun newInject(idRelic: Int): Bundle {
            return bundleOf(ID_RELIC to idRelic)
        }
    }
}