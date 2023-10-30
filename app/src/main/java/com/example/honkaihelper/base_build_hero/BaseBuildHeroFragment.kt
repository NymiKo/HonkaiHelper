package com.example.honkaihelper.base_build_hero

import androidx.navigation.fragment.findNavController
import com.example.honkaihelper.databinding.FragmentBaseBuildHeroBinding
import com.example.honkaihelper.fragments.BaseFragment

class BaseBuildHeroFragment :
    BaseFragment<FragmentBaseBuildHeroBinding>(FragmentBaseBuildHeroBinding::inflate) {
    override fun setupView() {
        setupToolbar()
    }

    override fun uiStateHandle() {

    }

    private fun setupToolbar() {
        binding.toolbarBaseBuildHero.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

}