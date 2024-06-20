package com.example.tanorami.profile.presentation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tanorami.App
import com.example.tanorami.R
import com.example.tanorami.auth.login.presentation.LoginFragment
import com.example.tanorami.databinding.FragmentProfileBinding
import com.example.tanorami.change_nickname.ChangeNicknameFragment
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.create_build_hero.CreateBuildHeroFragment
import com.example.tanorami.createteam.CreateTeamFragment
import com.example.tanorami.profile.presentation.adapter.ViewPagerTeamsAndBuildsAdapter
import com.example.tanorami.profile.presentation.adapter.ViewPagerTeamsAndBuildsListener
import com.example.tanorami.profile.data.model.User
import com.example.tanorami.profile.presentation.components.UserNotLoggedComponent
import com.example.tanorami.utils.TOKEN
import com.example.tanorami.utils.getFileName
import com.example.tanorami.utils.getSharedPrefUser
import com.example.tanorami.utils.gone
import com.example.tanorami.utils.invisible
import com.example.tanorami.utils.load
import com.example.tanorami.utils.loadWithPlaceholder
import com.example.tanorami.utils.toast
import com.example.tanorami.utils.visible
import com.google.android.material.tabs.TabLayoutMediator
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel> { viewModelFactory }
    private lateinit var mAdapter: ViewPagerTeamsAndBuildsAdapter
    private val selectImageIntent: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                loadAvatar(uri)
            }
        }

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

    private fun setupViewPager() {
        mAdapter = ViewPagerTeamsAndBuildsAdapter(object : ViewPagerTeamsAndBuildsListener {
            override fun onBuildClick(idBuild: Int) {
                //findNavController().navigate(R.id.createBuildHeroFragment, CreateBuildHeroFragment.newInstance(idBuild = idBuild))
            }

            override fun onTeamClick(idTeam: Int) {
                //findNavController().navigate(R.id.createTeamFragment, CreateTeamFragment.newInstance(idTeam))
            }
        })
    }


    private fun showDialogExitOfAccount() {
        AlertDialog.Builder(requireActivity())
            .setMessage(R.string.want_to_logout_of_your_account)
            .setPositiveButton(R.string.yes) { _, _ ->
                getSharedPrefUser().edit().putString(TOKEN, null).apply()
                findNavController().navigate(R.id.loginFragment)
            }
            .setNegativeButton(R.string.cancellation) { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}