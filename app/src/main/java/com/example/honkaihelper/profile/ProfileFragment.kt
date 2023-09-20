package com.example.honkaihelper.profile

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
    }

    private fun setupEnterButton() {
        binding.buttonGoLogin.setOnClickListener {
            findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(R.id.action_containerFragment_to_login_nav_graph)
        }
    }

    private fun checkToken() {
        if (!getSharedPrefUser().getString(TOKEN, "").isNullOrBlank()) {
            binding.groupNoLogin.gone()
            binding.imageUserAvatar.visible()
        } else {
            binding.groupNoLogin.visible()
        }
    }
}