package com.example.tanorami.base_build_hero.ui

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
import com.example.tanorami.base_build_hero.presentation.BaseBuildHeroViewModel
import com.example.tanorami.core.theme.AppTheme
import javax.inject.Inject

class BaseBuildHeroFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<BaseBuildHeroViewModel> { viewModelFactory }
    private val idHero get() = requireArguments().getInt(ID_HERO)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //(requireActivity().application as App).appComponent.baseBuildHeroComponent().create()
        //.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
//                    BaseBuildHeroScreen(
//                        idHero = idHero,
//                        viewModel = viewModel,
//                        navController = findNavController(),
//                    )
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
        private const val ID_HERO = "id"

        @JvmStatic
        fun newInstance(idHero: Int): Bundle {
            return bundleOf(ID_HERO to idHero)
        }
    }
}