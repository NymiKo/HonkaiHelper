package com.example.honkaihelper.profile

import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentProfileBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.login.LoginCallback
import com.example.honkaihelper.login.LoginFragment
import com.example.honkaihelper.utils.TOKEN
import com.example.honkaihelper.utils.getSharedPrefUser
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.visible

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun setupView() {
        setupEnterButton()
        menuItemClickHandler()
    }

    private fun setupEnterButton() {
        binding.buttonGoLogin.setOnClickListener {
            findNavController().navigate(R.id.login_nav_graph)
        }
    }

    override fun onResume() {
        super.onResume()
        checkToken()
    }

    private fun checkToken() {
        if (getSharedPrefUser().getString(TOKEN, "").isNullOrBlank()) {
            hideProfile()
        } else {
            showProfile()
        }
    }

    private fun menuItemClickHandler() {
        binding.toolbarProfile.setOnMenuItemClickListener {
            when(it.itemId) {
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
                getSharedPrefUser().edit().putString(TOKEN, "").apply()
                hideProfile()
            }
            .setNegativeButton(R.string.cancellation) { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }

    private fun showProfile() {
        binding.groupNoLogin.gone()
        binding.groupUserProfile.visible()
        binding.toolbarProfile.visible()
    }

    private fun hideProfile() {
        binding.groupNoLogin.visible()
        binding.groupUserProfile.gone()
        binding.toolbarProfile.gone()
    }
}