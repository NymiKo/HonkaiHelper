package com.example.honkaihelper.setupteam

import android.os.Bundle
import com.example.honkaihelper.databinding.FragmentSetupTeamBinding
import com.example.honkaihelper.fragments.BaseFragment
import com.example.honkaihelper.setupteam.adapter.SetupTeamAdapter

class SetupTeamFragment :
    BaseFragment<FragmentSetupTeamBinding>(FragmentSetupTeamBinding::inflate) {

    private lateinit var mAdapter: SetupTeamAdapter

    override fun setupView() {

    }

    override fun uiStateHandle() {

    }

    private fun setupRecyclerView() {
        
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SetupTeamFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}