package com.example.honkaihelper.container

import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentContainerBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class ContainerFragment :
    BaseFragment<FragmentContainerBinding>(FragmentContainerBinding::inflate) {

    private lateinit var mFragmentAdapter: ContainerFragmentAdapter

    override fun setupView() {
        setupViewPager()
        setupTabLayout()
    }

    override fun onResume() {
        super.onResume()
        binding.viewPagerContainer.setCurrentItem(0, false)
    }

    private fun setupViewPager() {
        mFragmentAdapter = ContainerFragmentAdapter(requireActivity())
        binding.viewPagerContainer.apply {
            adapter = mFragmentAdapter
            offscreenPageLimit = 3
        }
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayoutContainer, binding.viewPagerContainer) { tab, position ->
            val tabName = listOf(R.string.heroes, R.string.profile)
            tab.text = getString(tabName[position])
        }.attach()
    }

}