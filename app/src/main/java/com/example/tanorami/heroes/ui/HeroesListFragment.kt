package com.example.tanorami.heroes.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.heroes.presentation.HeroesListViewMode
import com.example.tanorami.load_data.ui.DATA_UPLOADED_KEY
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HeroesListViewMode> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        (requireActivity().application as App).appComponent.heroesListComponent().create()
//            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reenterTransition = MaterialFadeThrough().apply {
            duration = 500
        }
        val result = findNavController().currentBackStackEntry?.savedStateHandle?.getStateFlow(
            DATA_UPLOADED_KEY,
            false
        )
        lifecycleScope.launch {
            result?.collect {
                if (it) viewModel.getHeroesList()
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
                    HeroesListScreen(
                        viewModelFactory = viewModelFactory,
                        navController = findNavController()
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    companion object {
        private const val ARG_DATA_UPLOADED = "path"

        @JvmStatic
        fun newInstance(dataUploaded: Boolean): Bundle {
            return bundleOf(
                ARG_DATA_UPLOADED to dataUploaded
            )
        }
    }
}