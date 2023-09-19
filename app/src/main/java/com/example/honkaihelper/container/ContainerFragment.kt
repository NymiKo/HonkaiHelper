package com.example.honkaihelper.container

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentContainerBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class ContainerFragment : BaseFragment<FragmentContainerBinding>(FragmentContainerBinding::inflate) {

    override fun setupView() {
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        binding.viewPagerContainer.apply {
            adapter = ContainerFragmentAdapter(requireActivity())
            offscreenPageLimit = 2
        }
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayoutContainer, binding.viewPagerContainer) { tab, position ->
            val tabName = listOf(R.string.heroes, R.string.profile)
            tab.text = getString(tabName[position])
        }.attach()
    }

}