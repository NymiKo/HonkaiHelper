package com.example.tanorami.teams.ui

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
import com.example.tanorami.teams.presentation.TeamsListViewModel
import javax.inject.Inject

class TeamsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TeamsListViewModel> { viewModelFactory }

    private val idHero get() = requireArguments().getInt(ARG_ID_HERO)
    private val uid get() = requireArguments().getString(ARG_UID_TEAM)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.teamsListComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    TeamsListScreen(
                        idHero = idHero,
                        uid = uid,
                        viewModel = viewModel,
                        navController = findNavController(),
                    )
                }
            }
        }
    }

    companion object {
        private const val ARG_ID_HERO = "id_hero"
        private const val ARG_UID_TEAM = "uid"

        @JvmStatic
        fun newInstance(idHero: Int = -1, uid: String = ""): Bundle {
            return bundleOf(ARG_ID_HERO to idHero, ARG_UID_TEAM to uid)
        }
    }
}