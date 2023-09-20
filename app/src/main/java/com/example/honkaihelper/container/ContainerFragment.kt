package com.example.honkaihelper.container

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentContainerBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.profile.ProfileFragment
import com.google.android.material.tabs.TabLayoutMediator
import okhttp3.internal.notify

class ContainerFragment : BaseFragment<FragmentContainerBinding>(FragmentContainerBinding::inflate) {

    private lateinit var mFragmentAdapter: ContainerFragmentAdapter

    override fun setupView() {
        setupViewPager()
        setupTabLayout()
    }

    private fun setupViewPager() {
        mFragmentAdapter = ContainerFragmentAdapter(requireActivity())
        binding.viewPagerContainer.apply {
            adapter = mFragmentAdapter
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