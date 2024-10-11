package com.example.tanorami.viewing_users_build.ui

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
import com.example.tanorami.viewing_users_build.presentation.ViewingBuildHeroFromUserViewModel
import javax.inject.Inject

class ViewingBuildHeroFromUserFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ViewingBuildHeroFromUserViewModel> { viewModelFactory }

    private val idBuild get() = requireArguments().getLong(ARG_ID_BUILD)
    private val uid get() = requireArguments().getString(ARG_UID_BUILD, "")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.viewingUsersBuildComponent().create()
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
                    ViewingBuildHeroFromUserScreen(
                        idBuild = idBuild,
                        uid = uid,
                        viewModel = viewModel,
                        navController = findNavController()
                    )
                }
            }
        }
    }

    companion object {
        private const val ARG_ID_BUILD = "idBuild"
        private const val ARG_UID_BUILD = "uid"

        fun newInstance(idBuild: Long = -1L, uid: String = ""): Bundle {
            return bundleOf(ARG_ID_BUILD to idBuild, ARG_UID_BUILD to uid)
        }
    }
}