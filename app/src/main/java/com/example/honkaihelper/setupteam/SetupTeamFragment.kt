package com.example.honkaihelper.setupteam

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.FragmentSetupTeamBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.setupteam.adapter.SetupTeamAdapter
import com.example.honkaihelper.setupteam.adapter.SetupTeamListener
import com.example.honkaihelper.utils.toast

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
        setupRecyclerViewAdapter()
        mAdapter.heroesList = heroesList?.toList() ?: emptyList()
        binding.apply {
            recyclerSetupTeam.layoutManager = LinearLayoutManager(requireActivity())
            recyclerSetupTeam.setHasFixedSize(true)
            recyclerSetupTeam.adapter = mAdapter
        }
    }

    private fun setupRecyclerViewAdapter() {
        mAdapter = SetupTeamAdapter(object : SetupTeamListener {
            override fun onWeaponClick(heroPath: Int) {
                toast(requireActivity(), R.string.level_hero)
            }

            override fun onRelicClick() {
                toast(requireActivity(), R.string.eidolon_hero)
            }

            override fun onDecorationClick() {
                toast(requireActivity(), R.string.you_need_login)
            }
        })
    }

    companion object {

        private const val ARG_HEROES_LIST = "heroes_list"
        @JvmStatic
        fun newInstance(heroesList: List<Hero>):Bundle {
            return bundleOf(ARG_HEROES_LIST to heroesList)
        }
    }
}