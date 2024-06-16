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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val token = getSharedPrefUser().getString(TOKEN, "")
//        if (!token.isNullOrEmpty()) viewModel.onEvent(ProfileScreenEvents.FetchProfile)
//        uiStateHandle()
        //setupView()
    }

    fun setupView() {
        //setupViewPager()
        setupAvatarImageClickListener()
        //setupProfileTeamsAndBuilds()
    }

//    fun uiStateHandle() {
//        viewModel.uiState.observe(viewLifecycleOwner) {
//            when (it) {
//                is ProfileUiState.ERROR -> {
//                    toast(requireActivity(), it.message)
//                }
//
//                is ProfileUiState.LOADING -> {
//                    showLoading()
//                }
//
//                is ProfileUiState.NOT_AUTHORIZED -> {
//                    showUiNotAuthorized()
//                }
//
//                is ProfileUiState.SUCCESS -> {
//                    showUiProfile()
//                    loadProfile(it.user)
//                }
//            }
//        }
//    }

//    private fun showLoading() {
//        binding.composeView.gone()
//        binding.groupUserProfile.gone()
//        binding.toolbarProfile.invisible()
//        binding.shimmerLayoutProfile.showShimmer(true)
//    }
//
//    private fun showUiNotAuthorized() {
//        binding.shimmerLayoutProfile.gone()
//        binding.shimmerLayoutProfile.stopShimmer()
//        binding.groupUserProfile.gone()
//        binding.toolbarProfile.gone()
//        binding.composeView.visible()
//    }
//
//    private fun showUiProfile() {
//        binding.toolbarProfile.visible()
//        binding.groupUserProfile.visible()
//        binding.shimmerLayoutProfile.gone()
//        binding.shimmerLayoutProfile.stopShimmer()
//        binding.composeView.gone()
//    }
//
//    private fun loadProfile(user: User) {
//        binding.imageUserAvatar.loadWithPlaceholder(user.avatarUrl ?: "", R.drawable.ic_person)
//        if (!user.avatarUrl.isNullOrEmpty()) binding.imageUserAvatar.imageTintList = null
//        binding.textUserLogin.text = user.nickname
//        mAdapter.list = listOf(user.buildsHeroes, user.teamsList)
//    }

    private fun setupViewPager() {
        mAdapter = ViewPagerTeamsAndBuildsAdapter(object : ViewPagerTeamsAndBuildsListener {
            override fun onBuildClick(idBuild: Int) {
                //findNavController().navigate(R.id.createBuildHeroFragment, CreateBuildHeroFragment.newInstance(idBuild = idBuild))
            }

            override fun onTeamClick(idTeam: Int) {
                //findNavController().navigate(R.id.createTeamFragment, CreateTeamFragment.newInstance(idTeam))
            }
        })
        //binding.viewPagerProfileTeamsAndBuilds.adapter = mAdapter
    }

//    private fun setupProfileTeamsAndBuilds() {
//        TabLayoutMediator(binding.tabLayoutProfileTeams, binding.viewPagerProfileTeamsAndBuilds) { tab, position ->
//            when(position) {
//                0 -> tab.text = getString(R.string.my_builds)
//                1 -> tab.text = getString(R.string.my_teams)
//            }
//        }.attach()
//    }

    private fun setupAvatarImageClickListener() {
//        binding.imageUserAvatar.setOnClickListener {
//            selectImageIntent.launch("image/*")
//        }
    }

    private fun loadAvatar(uri: Uri) {
        //binding.imageUserAvatar.load(uri)
        val parcelFileDescriptor =
            requireActivity().contentResolver.openFileDescriptor(uri, "r", null)
        val inputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
        val file =
            File(requireActivity().cacheDir, requireActivity().contentResolver.getFileName(uri))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        viewModel.loadAvatar(file)
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