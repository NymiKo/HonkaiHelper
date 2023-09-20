package com.example.honkaihelper.container

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.honkaihelper.heroes.HeroesListFragment
import com.example.honkaihelper.profile.ProfileFragment

class ContainerFragmentAdapter(
    fragmentActivity: FragmentActivity
): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HeroesListFragment()
            1 -> ProfileFragment()
            else -> HeroesListFragment()
        }
    }
}