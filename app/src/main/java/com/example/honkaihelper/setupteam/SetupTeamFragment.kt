package com.example.honkaihelper.setupteam

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentSetupTeamBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.setupteam.adapter.SetupTeamAdapter

class SetupTeamFragment :
    BaseFragment<FragmentSetupTeamBinding>(FragmentSetupTeamBinding::inflate) {

    private lateinit var mAdapter: SetupTeamAdapter
    private val heroesList get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requireArguments().getParcelableArrayList(ARG_HEROES_LIST, Hero::class.java)
    } else {
        requireArguments().getParcelableArrayList(ARG_HEROES_LIST)
    }

    override fun setupView() {
        setupRecyclerView()
    }

    override fun uiStateHandle() {

    }

    private fun setupRecyclerView() {
        mAdapter = SetupTeamAdapter()
        mAdapter.heroesList = heroesList?.toList() ?: emptyList()
        binding.apply {
            recyclerSetupTeam.layoutManager = LinearLayoutManager(requireActivity())
            recyclerSetupTeam.setHasFixedSize(true)
            recyclerSetupTeam.adapter = mAdapter
        }
    }

    private fun setupHeroLevelSpinner() {

    }

    companion object {

        private const val ARG_HEROES_LIST = "heroes_list"
        @JvmStatic
        fun newInstance(heroesList: List<Hero>):Bundle {
            return bundleOf(ARG_HEROES_LIST to heroesList)
        }
    }
}