package com.example.honkaihelper.profile

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.App
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentProfileBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.profile.data.model.User
import com.example.honkaihelper.utils.TOKEN
import com.example.honkaihelper.utils.getDrawable
import com.example.honkaihelper.utils.getSharedPrefUser
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.invisible
import com.example.honkaihelper.utils.load
import com.example.honkaihelper.utils.loadWithPlaceholder
import com.example.honkaihelper.utils.toast
import com.example.honkaihelper.utils.visible

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel by viewModels<ProfileViewModel> { viewModelFactory }

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
    }

    override fun uiStateHandle() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when(it) {
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
        binding.imageUserAvatar.loadWithPlaceholder(user.avatar, R.drawable.ic_person)
        binding.textUserLogin.text = user.login
    }

    private fun setupEnterButton() {
        binding.buttonGoToAuthorization.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
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