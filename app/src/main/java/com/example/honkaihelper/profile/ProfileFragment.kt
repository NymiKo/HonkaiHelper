package com.example.honkaihelper.profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentProfileBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.profile.adapter.ProfileTeamsListAdapter
import com.example.honkaihelper.profile.data.model.User
import com.example.honkaihelper.utils.TOKEN
import com.example.honkaihelper.utils.getFileName
import com.example.honkaihelper.utils.getSharedPrefUser
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.invisible
import com.example.honkaihelper.utils.load
import com.example.honkaihelper.utils.loadWithPlaceholder
import com.example.honkaihelper.utils.toast
import com.example.honkaihelper.utils.visible
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel by viewModels<ProfileViewModel> { viewModelFactory }
    private lateinit var mAdapter: ProfileTeamsListAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val token = getSharedPrefUser().getString(TOKEN, "")
        if (!token.isNullOrEmpty()) viewModel.getProfile()
    }

    override fun setupView() {
        setupEnterButton()
        menuItemClickHandler()
        setupAvatarImageClickListener()
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is ProfileUiState.ERROR -> {
                    toast(requireActivity(), it.message)
                }

                is ProfileUiState.LOADING -> {
                    showLoading()
                }

                is ProfileUiState.NOT_AUTHORIZED -> {
                    showUiNotAuthorized()
                }

                is ProfileUiState.SUCCESS -> {
                    showUiProfile()
                    setupRecyclerView()
                    loadProfile(it.user)
                }
            }
        }
    }

    private fun showLoading() {
        binding.groupNotAuthorized.gone()
        binding.groupUserProfile.gone()
        binding.toolbarProfile.invisible()
        binding.shimmerLayoutProfile.showShimmer(true)
    }

    private fun showUiNotAuthorized() {
        binding.shimmerLayoutProfile.gone()
        binding.shimmerLayoutProfile.stopShimmer()
        binding.groupUserProfile.gone()
        binding.toolbarProfile.gone()
        binding.groupNotAuthorized.visible()
    }

    private fun showUiProfile() {
        binding.toolbarProfile.visible()
        binding.groupUserProfile.visible()
        binding.shimmerLayoutProfile.gone()
        binding.shimmerLayoutProfile.stopShimmer()
        binding.groupNotAuthorized.gone()
    }

    private fun loadProfile(user: User) {
        binding.imageUserAvatar.loadWithPlaceholder(user.avatarUrl, R.drawable.ic_person)
        binding.imageUserAvatar.imageTintList = null
        binding.textUserLogin.text = user.login
        binding.textTeams.text = getString(R.string.my_teams)
        mAdapter.mTeamsList = user.teamsList ?: emptyList()
        Log.e("TEAMS", user.teamsList.toString())
    }

    private fun setupRecyclerView() {
        mAdapter = ProfileTeamsListAdapter()
        binding.recyclerTeamsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mAdapter
        }
    }

    private fun setupAvatarImageClickListener() {
        binding.imageUserAvatar.setOnClickListener {
            selectImageIntent.launch("image/*")
        }
    }

    private fun setupEnterButton() {
        binding.buttonGoToAuthorization.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    private fun loadAvatar(uri: Uri) {
        binding.imageUserAvatar.load(uri)
        val parcelFileDescriptor =
            requireActivity().contentResolver.openFileDescriptor(uri, "r", null)
        val inputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
        val file =
            File(requireActivity().cacheDir, requireActivity().contentResolver.getFileName(uri))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        viewModel.loadAvatar(file)
    }

    private fun menuItemClickHandler() {
        binding.toolbarProfile.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.exit_of_account -> {
                    showDialogExitOfAccount()
                }
            }
            true
        }
    }

    private fun showDialogExitOfAccount() {
        AlertDialog.Builder(requireActivity())
            .setMessage(R.string.want_to_logout_of_your_account)
            .setPositiveButton(R.string.yes) { _, _ ->
                getSharedPrefUser().edit().putString(TOKEN, null).apply()
                viewModel.logoutAccount()
            }
            .setNegativeButton(R.string.cancellation) { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }
}