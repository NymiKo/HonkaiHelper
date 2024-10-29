package com.example.tanorami.createteam.ui

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
import com.example.tanorami.createteam.presentation.CreateTeamViewModel
import javax.inject.Inject

class CreateTeamFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CreateTeamViewModel> { viewModelFactory }

    private val idTeam get() = requireArguments().getLong(ARG_ID_TEAM, -1)

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        (requireActivity().application as App).appComponent.createTeamComponent().create()
//            .inject(this)
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
        const val ARG_ID_TEAM = "id_team"

        fun newInstance(idTeam: Long = -1): Bundle {
            return bundleOf(ARG_ID_TEAM to idTeam)
        }
    }
}