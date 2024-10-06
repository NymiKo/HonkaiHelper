package com.example.tanorami.info_about_relic.ui

import android.content.Context
import android.graphics.Color
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
import com.example.tanorami.R
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.info_about_relic.presentation.RelicInfoViewModel
import com.google.android.material.transition.MaterialContainerTransform
import javax.inject.Inject

class InfoAboutRelicFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val idRelic get() = requireArguments().getInt(ID_RELIC)
    private val viewModel by viewModels<RelicInfoViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.relicInfoComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.navHostFragment
            duration = 500
            scrimColor = Color.TRANSPARENT
        }
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
                    InfoAboutRelicScreen(
                        idRelic = idRelic,
                        viewModel = viewModel,
                        navController = findNavController(),
                    )
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