package com.example.honkaihelper.profile

import androidx.navigation.Navigation.findNavController
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentProfileBinding
import com.example.honkaihelper.fragments.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun setupView() {
        setupEnterButton()
    }

    private fun setupEnterButton() {
        binding.buttonGoLogin.setOnClickListener {
            findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(R.id.action_containerFragment_to_login_nav_graph)
        }
    }
}