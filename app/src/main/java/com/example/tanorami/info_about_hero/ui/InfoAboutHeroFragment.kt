package com.example.tanorami.info_about_hero.ui

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
import com.example.tanorami.info_about_hero.presentation.InfoAboutHeroViewModel
import javax.inject.Inject

class InfoAboutHeroFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<InfoAboutHeroViewModel> { viewModelFactory }
    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //(requireActivity().application as App).appComponent.infoAboutHeroComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

            }
        }
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"

        @JvmStatic
        fun newInstance(idHero: Int): Bundle {
            return bundleOf(ARG_ID_HERO to idHero)
        }
    }
}