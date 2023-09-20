package com.example.honkaihelper.profile

import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation.findNavController
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentProfileBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.utils.TOKEN
import com.example.honkaihelper.utils.getSharedPrefUser
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.visible

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun setupView() {
        checkToken()
        setupEnterButton()
        exitAccount()
    }

    private fun setupEnterButton() {
        binding.buttonGoLogin.setOnClickListener {
            findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(R.id.login_nav_graph)
        }
    }

    private fun checkToken() {
        if (getSharedPrefUser().getString(TOKEN, "").isNullOrBlank()) {
            hideProfile()
        } else {
            showProfile()
        }
    }

    private fun exitAccount() {
        binding.buttonExitOfAccount.setOnClickListener {
            AlertDialog.Builder(requireActivity())
                .setMessage(R.string.exit_of_account)
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
    }

    private fun showProfile() {
        binding.groupNoLogin.gone()
        binding.groupUserProfile.visible()
    }

    private fun hideProfile() {
        binding.groupNoLogin.visible()
        binding.groupUserProfile.gone()
    }
}