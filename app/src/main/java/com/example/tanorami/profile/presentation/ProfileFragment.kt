package com.example.tanorami.profile.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.change_nickname.presentation.ChangeNicknameFragment
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.create_build_hero.CreateBuildHeroFragment
import com.example.tanorami.createteam.CreateTeamFragment
import com.example.tanorami.databinding.FragmentProfileBinding
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.profileComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater).apply {
            composeView.setContent {
                AppTheme {
                    ProfileScreen(
                        viewModel = viewModel,
                        onChangeNicknameScreen = { nickname ->
                            findNavController().navigate(R.id.changeNicknameFragment, ChangeNicknameFragment.newInject(nickname))
                        },
                        onEditBuildHeroScreen = { idBuild ->
                            findNavController().navigate(R.id.createBuildHeroFragment, CreateBuildHeroFragment.newInstance(idBuild = idBuild))
                        },
                        onEditTeamScreen = { idTeam ->
                            findNavController().navigate(R.id.createTeamFragment, CreateTeamFragment.newInstance(idTeam))
                        },
                        onLoginScreen = {
                            findNavController().navigate(R.id.loginFragment)
                        },
                    )
                }
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}